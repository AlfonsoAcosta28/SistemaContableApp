package com.mycompany.mavenproject1.sat.modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Operación "SolicitaDescarga{Emitidos|Recibidos}": pide al SAT que arme el
 * paquete de CFDI para el rango de fechas indicado.
 */
public class SatSolicitudService {

    private static final String URL = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/SolicitaDescargaService.svc";
    private static final String SOAP_ACTION_BASE = "http://DescargaMasivaTerceros.sat.gob.mx/ISolicitaDescargaService/SolicitaDescarga";

    private static final String NS_SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
    private static final String NS_DES = "http://DescargaMasivaTerceros.sat.gob.mx";

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public String solicitar(FielCredentials credenciales, String token, SolicitudDescargaParams params) throws SatDescargaMasivaException {
        boolean emitidos = params.getTipo() == TipoConsultaCfdi.EMITIDOS;
        String operacion = "SolicitaDescarga" + (emitidos ? "Emitidos" : "Recibidos");

        Document doc = SatSoapUtil.nuevoDocumento();

        Element envelope = doc.createElementNS(NS_SOAP, "s:Envelope");
        doc.appendChild(envelope);
        Element body = doc.createElementNS(NS_SOAP, "s:Body");
        envelope.appendChild(body);

        Element operacionEl = doc.createElementNS(NS_DES, "des:" + operacion);
        body.appendChild(operacionEl);

        Element solicitud = doc.createElementNS(NS_DES, "des:solicitud");
        solicitud.setAttribute("Id", "_0");
        solicitud.setAttribute("RfcSolicitante", params.getRfcSolicitante());
        solicitud.setAttribute("FechaInicial", FORMATO_FECHA.format(LocalDateTime.of(params.getFechaInicial(), LocalTime.MIN)));
        solicitud.setAttribute("FechaFinal", FORMATO_FECHA.format(LocalDateTime.of(params.getFechaFinal(), LocalTime.of(23, 59, 59))));
        solicitud.setAttribute("TipoSolicitud", "CFDI");
        if (emitidos) {
            solicitud.setAttribute("RfcEmisor", params.getRfcSolicitante());
        } else {
            solicitud.setAttribute("RfcReceptor", params.getRfcSolicitante());
        }
        operacionEl.appendChild(solicitud);

        XmlDSigSigner.firmarEnvolvente(doc, solicitud, "_0", credenciales.getPrivateKey(), credenciales.getCertificate());

        Document respuesta = SatSoapUtil.postSoap(URL, SOAP_ACTION_BASE + (emitidos ? "Emitidos" : "Recibidos"), doc,
                "WRAP access_token=\"" + token + "\"");

        String codEstatus = SatSoapUtil.textoDescendiente(respuesta.getDocumentElement(), "CodEstatus");
        String idSolicitud = SatSoapUtil.textoDescendiente(respuesta.getDocumentElement(), "IdSolicitud");
        String mensaje = SatSoapUtil.textoDescendiente(respuesta.getDocumentElement(), "Mensaje");

        if (idSolicitud == null || idSolicitud.isBlank()) {
            throw new SatDescargaMasivaException("El SAT no aceptó la solicitud de descarga"
                    + (codEstatus != null ? " (código " + codEstatus + ")" : "") + ": "
                    + (mensaje != null ? mensaje : "sin mensaje adicional."));
        }
        return idSolicitud.trim();
    }
}
