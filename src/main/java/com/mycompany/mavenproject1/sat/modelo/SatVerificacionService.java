package com.mycompany.mavenproject1.sat.modelo;

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Operación "VerificaSolicitud": consulta el avance de una solicitud de
 * descarga previamente aceptada por el SAT.
 */
public class SatVerificacionService {

    private static final String URL = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/VerificaSolicitudDescargaService.svc";
    private static final String SOAP_ACTION
            = "http://DescargaMasivaTerceros.sat.gob.mx/IVerificaSolicitudDescargaService/VerificaSolicitud";

    private static final String NS_SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
    private static final String NS_DES = "http://DescargaMasivaTerceros.sat.gob.mx";

    public VerificacionResultado verificar(FielCredentials credenciales, String token, String rfcSolicitante, String idSolicitud)
            throws SatDescargaMasivaException {
        Document doc = SatSoapUtil.nuevoDocumento();

        Element envelope = doc.createElementNS(NS_SOAP, "s:Envelope");
        doc.appendChild(envelope);
        Element body = doc.createElementNS(NS_SOAP, "s:Body");
        envelope.appendChild(body);

        Element verifica = doc.createElementNS(NS_DES, "des:VerificaSolicitudDescarga");
        body.appendChild(verifica);

        Element solicitud = doc.createElementNS(NS_DES, "des:solicitud");
        solicitud.setAttribute("Id", "_0");
        solicitud.setAttribute("IdSolicitud", idSolicitud);
        solicitud.setAttribute("RfcSolicitante", rfcSolicitante);
        verifica.appendChild(solicitud);

        XmlDSigSigner.firmarEnvolvente(doc, solicitud, "_0", credenciales.getPrivateKey(), credenciales.getCertificate());

        Document respuesta = SatSoapUtil.postSoap(URL, SOAP_ACTION, doc, "WRAP access_token=\"" + token + "\"");
        Element raiz = respuesta.getDocumentElement();

        String estadoTxt = SatSoapUtil.textoDescendiente(raiz, "EstadoSolicitud");
        String codigoEstado = SatSoapUtil.textoDescendiente(raiz, "CodigoEstadoSolicitud");
        String mensaje = SatSoapUtil.textoDescendiente(raiz, "Mensaje");
        List<String> idsPaquetes = SatSoapUtil.textosDescendientes(raiz, "string");

        int estado;
        try {
            estado = estadoTxt != null ? Integer.parseInt(estadoTxt.trim()) : -1;
        } catch (NumberFormatException ex) {
            estado = -1;
        }

        if (estado == -1) {
            throw new SatDescargaMasivaException("El SAT no devolvió un estado válido al verificar la solicitud"
                    + (mensaje != null ? ": " + mensaje : "."));
        }

        return new VerificacionResultado(estado, codigoEstado, mensaje, idsPaquetes);
    }
}
