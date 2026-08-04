package com.mycompany.mavenproject1.sat.modelo;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Operación "Autentica" del servicio de Descarga Masiva del SAT: firma un
 * WS-Security Timestamp con la e.firma y obtiene a cambio un token vigente
 * ~5 minutos que se usa como Authorization en las demás operaciones.
 */
public class SatAutenticacionService {

    private static final String URL = "https://cfdidescargamasivasolicitud.clouda.sat.gob.mx/Autenticacion/Autenticacion.svc";
    private static final String SOAP_ACTION = "http://DescargaMasivaTerceros.gob.mx/IAutenticacion/Autentica";

    private static final String NS_SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
    private static final String NS_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String NS_WSU = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
    private static final String NS_DES = "http://DescargaMasivaTerceros.gob.mx";

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public String autenticar(FielCredentials credenciales) throws SatDescargaMasivaException {
        Document doc = SatSoapUtil.nuevoDocumento();

        Element envelope = doc.createElementNS(NS_SOAP, "s:Envelope");
        doc.appendChild(envelope);

        Element header = doc.createElementNS(NS_SOAP, "s:Header");
        envelope.appendChild(header);

        Element security = doc.createElementNS(NS_WSSE, "o:Security");
        security.setAttributeNS(NS_SOAP, "s:mustUnderstand", "1");
        header.appendChild(security);

        Instant ahora = Instant.now();
        Element timestamp = doc.createElementNS(NS_WSU, "u:Timestamp");
        timestamp.setAttributeNS(NS_WSU, "u:Id", "_0");
        Element created = doc.createElementNS(NS_WSU, "u:Created");
        created.setTextContent(FORMATO_FECHA.format(ahora));
        Element expires = doc.createElementNS(NS_WSU, "u:Expires");
        expires.setTextContent(FORMATO_FECHA.format(ahora.plusSeconds(300)));
        timestamp.appendChild(created);
        timestamp.appendChild(expires);
        security.appendChild(timestamp);

        Element bst = doc.createElementNS(NS_WSSE, "o:BinarySecurityToken");
        bst.setAttributeNS(NS_WSU, "u:Id", "BST");
        bst.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        bst.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        try {
            bst.setTextContent(Base64.getEncoder().encodeToString(credenciales.getCertificate().getEncoded()));
        } catch (java.security.cert.CertificateEncodingException ex) {
            throw new SatDescargaMasivaException("El certificado .cer no se pudo codificar para la autenticación.", ex);
        }
        security.appendChild(bst);

        XmlDSigSigner.firmarTimestampWsSecurity(doc, security, timestamp, "_0", "BST", credenciales.getPrivateKey());

        Element body = doc.createElementNS(NS_SOAP, "s:Body");
        envelope.appendChild(body);
        Element autentica = doc.createElementNS(NS_DES, "Autentica");
        body.appendChild(autentica);

        Document respuesta = SatSoapUtil.postSoap(URL, SOAP_ACTION, doc, null);
        String token = SatSoapUtil.textoDescendiente(respuesta.getDocumentElement(), "AutenticaResult");
        if (token == null || token.isBlank()) {
            throw new SatDescargaMasivaException("El SAT no devolvió un token de autenticación. Verifique que la e.firma esté vigente.");
        }
        return token.trim();
    }
}
