package com.mycompany.mavenproject1.sat.modelo;

import java.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Operación "Descargar": obtiene el paquete (ZIP en base64 con los XML de
 * los CFDI) asociado a un IdPaquete ya disponible (solicitud Terminada).
 */
public class SatDescargaService {

    private static final String URL = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/DescargaMasivaService.svc";
    private static final String SOAP_ACTION
            = "http://DescargaMasivaTerceros.sat.gob.mx/IDescargaMasivaTercerosService/Descargar";

    private static final String NS_SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
    private static final String NS_DES = "http://DescargaMasivaTerceros.sat.gob.mx";

    public byte[] descargar(FielCredentials credenciales, String token, String rfcSolicitante, String idPaquete)
            throws SatDescargaMasivaException {
        Document doc = SatSoapUtil.nuevoDocumento();

        Element envelope = doc.createElementNS(NS_SOAP, "s:Envelope");
        doc.appendChild(envelope);
        Element body = doc.createElementNS(NS_SOAP, "s:Body");
        envelope.appendChild(body);

        Element peticionEntrada = doc.createElementNS(NS_DES, "des:PeticionDescargaMasivaTercerosEntrada");
        body.appendChild(peticionEntrada);

        Element peticion = doc.createElementNS(NS_DES, "des:peticionDescarga");
        peticion.setAttribute("Id", "_0");
        peticion.setAttribute("IdPaquete", idPaquete);
        peticion.setAttribute("RfcSolicitante", rfcSolicitante);
        peticionEntrada.appendChild(peticion);

        XmlDSigSigner.firmarEnvolvente(doc, peticion, "_0", credenciales.getPrivateKey(), credenciales.getCertificate());

        Document respuesta = SatSoapUtil.postSoap(URL, SOAP_ACTION, doc, "WRAP access_token=\"" + token + "\"");
        Element raiz = respuesta.getDocumentElement();

        String paqueteBase64 = SatSoapUtil.textoDescendiente(raiz, "Paquete");
        if (paqueteBase64 == null || paqueteBase64.isBlank()) {
            String mensaje = SatSoapUtil.textoDescendiente(raiz, "Mensaje");
            throw new SatDescargaMasivaException("El SAT no devolvió el paquete " + idPaquete
                    + (mensaje != null ? ": " + mensaje : "."));
        }
        return Base64.getMimeDecoder().decode(paqueteBase64.trim());
    }
}
