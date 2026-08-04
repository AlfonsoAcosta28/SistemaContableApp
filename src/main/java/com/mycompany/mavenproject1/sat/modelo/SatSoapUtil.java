package com.mycompany.mavenproject1.sat.modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Utilidades comunes de bajo nivel para hablar con los 4 servicios SOAP de
 * Descarga Masiva del SAT: armar/parsear XML y hacer el POST HTTP.
 */
final class SatSoapUtil {

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .build();

    private SatSoapUtil() {
    }

    static Document nuevoDocumento() throws SatDescargaMasivaException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            return factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException ex) {
            throw new SatDescargaMasivaException("No se pudo construir la petición XML para el SAT.", ex);
        }
    }

    static Document parsear(byte[] xml) throws SatDescargaMasivaException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xml));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new SatDescargaMasivaException("El SAT respondió con un XML que no se pudo interpretar.", ex);
        }
    }

    static byte[] aBytes(Document doc) throws SatDescargaMasivaException {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            transformer.transform(new DOMSource(doc), new StreamResult(out));
            return out.toByteArray();
        } catch (TransformerException ex) {
            throw new SatDescargaMasivaException("No se pudo serializar la petición XML para el SAT.", ex);
        }
    }

    /**
     * Envía el sobre SOAP y regresa el cuerpo de la respuesta ya parseado.
     * Si el SAT responde con un SOAP Fault (o cualquier otro cuerpo XML con
     * código de error &gt;= 300), igual se intenta parsear y extraer el
     * mensaje de falla para mostrarlo al usuario.
     */
    static Document postSoap(String url, String soapAction, Document envelope, String authorizationHeader)
            throws SatDescargaMasivaException {
        byte[] cuerpo = aBytes(envelope);
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(url))
                    .timeout(Duration.ofSeconds(60))
                    .header("Content-Type", "text/xml; charset=utf-8")
                    .header("SOAPAction", soapAction)
                    .POST(HttpRequest.BodyPublishers.ofByteArray(cuerpo));
            if (authorizationHeader != null) {
                builder.header("Authorization", authorizationHeader);
            }
            HttpResponse<byte[]> respuesta = HTTP_CLIENT.send(builder.build(), HttpResponse.BodyHandlers.ofByteArray());

            if (respuesta.statusCode() >= 300) {
                String mensajeFalla = intentarExtraerMensajeFalla(respuesta.body());
                throw new SatDescargaMasivaException("El SAT rechazó la petición (HTTP " + respuesta.statusCode() + "): "
                        + (mensajeFalla != null ? mensajeFalla : "sin detalle adicional."));
            }
            return parsear(respuesta.body());
        } catch (IOException | InterruptedException ex) {
            if (ex instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new SatDescargaMasivaException("No se pudo conectar con el servicio del SAT: " + ex.getMessage(), ex);
        }
    }

    private static String intentarExtraerMensajeFalla(byte[] cuerpo) {
        try {
            Document doc = parsear(cuerpo);
            String texto = textoDescendiente(doc.getDocumentElement(), "faultstring");
            if (texto == null) {
                texto = textoDescendiente(doc.getDocumentElement(), "Reason");
            }
            return texto;
        } catch (SatDescargaMasivaException ex) {
            return null;
        }
    }

    /** Busca el primer descendiente cuyo local name coincida (sin importar prefijo/namespace) y regresa su texto. */
    static String textoDescendiente(Element raiz, String localName) {
        NodeList lista = raiz.getElementsByTagNameNS("*", localName);
        if (lista.getLength() == 0) {
            return null;
        }
        return lista.item(0).getTextContent();
    }

    /** Regresa el texto de todos los descendientes cuyo local name coincida. */
    static java.util.List<String> textosDescendientes(Element raiz, String localName) {
        NodeList lista = raiz.getElementsByTagNameNS("*", localName);
        java.util.List<String> valores = new java.util.ArrayList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Node n = lista.item(i);
            String texto = n.getTextContent();
            if (texto != null && !texto.isBlank()) {
                valores.add(texto.trim());
            }
        }
        return valores;
    }
}
