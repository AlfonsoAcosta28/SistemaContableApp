package com.mycompany.mavenproject1.sat.modelo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Fachada que orquesta el flujo completo de descarga masiva de CFDI para un
 * {@link TipoConsultaCfdi} (Emitidos o Recibidos): autenticación, solicitud,
 * verificación (con polling) y descarga/extracción de cada paquete. Es la
 * única clase del modelo que usa el Controlador.
 */
public class DescargaMasivaCfdiService {

    private static final long INTERVALO_VERIFICACION_MS = 10_000;
    private static final long TIEMPO_MAXIMO_ESPERA_MS = 15 * 60 * 1000;
    /** El token de autenticación del SAT es válido ~5 minutos; se renueva antes de ese límite. */
    private static final long VIGENCIA_TOKEN_MS = 4 * 60 * 1000;

    private final SatAutenticacionService autenticacionService = new SatAutenticacionService();
    private final SatSolicitudService solicitudService = new SatSolicitudService();
    private final SatVerificacionService verificacionService = new SatVerificacionService();
    private final SatDescargaService descargaService = new SatDescargaService();

    /**
     * Ejecuta el flujo completo y deja los XML de los CFDI descargados
     * directamente en {@code carpetaDestino}. Regresa el número de XML
     * extraídos. Debe ejecutarse en un hilo de fondo: es una llamada
     * bloqueante (hace polling contra el SAT).
     */
    public int descargar(FielCredentials credenciales, SolicitudDescargaParams params, File carpetaDestino, Consumer<String> progreso)
            throws SatDescargaMasivaException {
        String tipoLegible = params.getTipo() == TipoConsultaCfdi.EMITIDOS ? "emitidos" : "recibidos";

        progreso.accept("Autenticando con el SAT (CFDI " + tipoLegible + ")...");
        String token = autenticacionService.autenticar(credenciales);
        long tokenObtenidoEn = System.currentTimeMillis();

        progreso.accept("Solicitando descarga de CFDI " + tipoLegible + "...");
        String idSolicitud = solicitudService.solicitar(credenciales, token, params);
        progreso.accept("Solicitud registrada (Id " + idSolicitud + "). Esperando a que el SAT prepare el paquete...");

        long inicioEspera = System.currentTimeMillis();
        VerificacionResultado resultado;
        while (true) {
            if (System.currentTimeMillis() - tokenObtenidoEn > VIGENCIA_TOKEN_MS) {
                token = autenticacionService.autenticar(credenciales);
                tokenObtenidoEn = System.currentTimeMillis();
            }

            resultado = verificacionService.verificar(credenciales, token, credenciales.getRfc(), idSolicitud);

            if (resultado.isTerminada()) {
                break;
            }
            if (!resultado.isEnProceso()) {
                throw new SatDescargaMasivaException("El SAT no pudo generar el paquete de CFDI " + tipoLegible
                        + " (estado " + resultado.getEstadoSolicitud() + "): "
                        + (resultado.getMensaje() != null ? resultado.getMensaje() : "sin detalle adicional."));
            }
            if (System.currentTimeMillis() - inicioEspera > TIEMPO_MAXIMO_ESPERA_MS) {
                throw new SatDescargaMasivaException("El SAT sigue procesando la solicitud de CFDI " + tipoLegible
                        + " (Id " + idSolicitud + ") después de 15 minutos. La solicitud sigue activa en el SAT; "
                        + "puede intentar de nuevo más tarde.");
            }

            progreso.accept("El SAT sigue preparando el paquete de CFDI " + tipoLegible + "...");
            esperar(INTERVALO_VERIFICACION_MS);
        }

        if (resultado.getIdsPaquetes().isEmpty()) {
            progreso.accept("El SAT no encontró CFDI " + tipoLegible + " en el rango de fechas indicado.");
            return 0;
        }

        int totalXml = 0;
        int numeroPaquete = 0;
        int totalPaquetes = resultado.getIdsPaquetes().size();
        for (String idPaquete : resultado.getIdsPaquetes()) {
            numeroPaquete++;
            if (System.currentTimeMillis() - tokenObtenidoEn > VIGENCIA_TOKEN_MS) {
                token = autenticacionService.autenticar(credenciales);
                tokenObtenidoEn = System.currentTimeMillis();
            }
            progreso.accept("Descargando paquete " + numeroPaquete + " de " + totalPaquetes + " (CFDI " + tipoLegible + ")...");
            byte[] zip = descargaService.descargar(credenciales, token, credenciales.getRfc(), idPaquete);
            totalXml += extraerZipEnCarpeta(zip, carpetaDestino);
        }
        return totalXml;
    }

    private static void esperar(long milisegundos) throws SatDescargaMasivaException {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new SatDescargaMasivaException("Se canceló la espera de la descarga masiva.", ex);
        }
    }

    /** Extrae sólo los .xml del ZIP en carpetaDestino, evitando zip-slip y colisiones de nombre. */
    private static int extraerZipEnCarpeta(byte[] zipBytes, File carpetaDestino) throws SatDescargaMasivaException {
        int contador = 0;
        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zipBytes))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory() || !entry.getName().toLowerCase().endsWith(".xml")) {
                    continue;
                }
                String nombreBase = new File(entry.getName()).getName();
                File destino = new File(carpetaDestino, nombreBase);
                int sufijo = 1;
                while (destino.exists()) {
                    String sinExtension = nombreBase.replaceFirst("(?i)\\.xml$", "");
                    destino = new File(carpetaDestino, sinExtension + "_" + (sufijo++) + ".xml");
                }
                Files.copy(zis, destino.toPath());
                contador++;
            }
        } catch (IOException ex) {
            throw new SatDescargaMasivaException("No se pudo extraer el paquete de CFDI descargado.", ex);
        }
        return contador;
    }
}
