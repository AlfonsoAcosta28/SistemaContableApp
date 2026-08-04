package com.mycompany.mavenproject1.sat.controlador;

import com.mycompany.mavenproject1.sat.modelo.DescargaMasivaCfdiService;
import com.mycompany.mavenproject1.sat.modelo.FielCredentials;
import com.mycompany.mavenproject1.sat.modelo.SatDescargaMasivaException;
import com.mycompany.mavenproject1.sat.modelo.SolicitudDescargaParams;
import com.mycompany.mavenproject1.sat.modelo.TipoConsultaCfdi;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 * Único punto de entrada que la Vista usa para disparar una descarga masiva
 * de CFDI. No conoce a VistaP: se comunica exclusivamente a través de
 * {@link SatDescargaListener}, siempre desde el Event Dispatch Thread.
 */
public class SatDescargaMasivaController {

    public void descargar(File archivoCer, File archivoKey, char[] password, LocalDate fechaInicial, LocalDate fechaFinal,
            boolean incluirEmitidos, boolean incluirRecibidos, File carpetaDestino, SatDescargaListener listener) {

        SwingWorker<Integer, String> worker = new SwingWorker<>() {
            private SatDescargaMasivaException error;

            @Override
            protected Integer doInBackground() {
                int total = 0;
                try {
                    DescargaMasivaCfdiService servicio = new DescargaMasivaCfdiService();
                    FielCredentials credenciales = FielCredentials.cargar(archivoCer, archivoKey, password);

                    if (incluirEmitidos) {
                        total += servicio.descargar(credenciales,
                                new SolicitudDescargaParams(credenciales.getRfc(), fechaInicial, fechaFinal, TipoConsultaCfdi.EMITIDOS),
                                carpetaDestino, this::publish);
                    }
                    if (incluirRecibidos) {
                        total += servicio.descargar(credenciales,
                                new SolicitudDescargaParams(credenciales.getRfc(), fechaInicial, fechaFinal, TipoConsultaCfdi.RECIBIDOS),
                                carpetaDestino, this::publish);
                    }
                } catch (SatDescargaMasivaException ex) {
                    error = ex;
                } finally {
                    Arrays.fill(password, '\0');
                }
                return total;
            }

            @Override
            protected void process(List<String> chunks) {
                if (!chunks.isEmpty()) {
                    listener.onProgreso(chunks.get(chunks.size() - 1));
                }
            }

            @Override
            protected void done() {
                if (error != null) {
                    listener.onError(error.getMessage());
                    return;
                }
                try {
                    listener.onExito(carpetaDestino, get());
                } catch (InterruptedException | ExecutionException ex) {
                    listener.onError("Ocurrió un error inesperado durante la descarga: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }
}
