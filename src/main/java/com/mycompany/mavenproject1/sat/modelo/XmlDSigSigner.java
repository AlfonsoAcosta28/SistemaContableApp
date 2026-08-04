package com.mycompany.mavenproject1.sat.modelo;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Firma XML-DSig (RSA-SHA1 / Exclusive C14N) tal como la exige el Servicio
 * Web de Descarga Masiva de CFDI del SAT. Se usa tanto para firmar el
 * elemento raíz de las peticiones (solicitud/verificación/descarga, con el
 * certificado embebido en el KeyInfo) como para firmar el Timestamp del
 * header WS-Security de Autenticación (con un KeyInfo que referencia el
 * BinarySecurityToken).
 */
public final class XmlDSigSigner {

    private static final String RSA_SHA1 = "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
    private static final String WSSE_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    private static final String X509_TOKEN_VALUETYPE
            = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3";

    private XmlDSigSigner() {
    }

    /**
     * Firma {@code elemento} (que debe tener el atributo {@code Id} con
     * valor {@code id}) de forma "enveloped": agrega un &lt;ds:Signature&gt;
     * como último hijo del propio elemento, con el certificado embebido en
     * el KeyInfo. Es el patrón usado por Solicitud/Verificación/Descarga.
     */
    public static void firmarEnvolvente(Document doc, Element elemento, String id, PrivateKey llave, X509Certificate certificado)
            throws SatDescargaMasivaException {
        try {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            List<Transform> transforms = List.of(
                    fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null),
                    fac.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null));

            Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transforms, null, null);

            SignedInfo signedInfo = fac.newSignedInfo(
                    fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(RSA_SHA1, null),
                    Collections.singletonList(ref));

            KeyInfoFactory kif = fac.getKeyInfoFactory();
            X509Data x509Data = kif.newX509Data(Collections.singletonList(certificado));
            KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(x509Data));

            XMLSignature signature = fac.newXMLSignature(signedInfo, keyInfo);

            DOMSignContext signContext = new DOMSignContext(llave, elemento);
            signContext.setDefaultNamespacePrefix("ds");
            signContext.setIdAttributeNS(elemento, null, "Id");

            signature.sign(signContext);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            throw new SatDescargaMasivaException("No se pudo firmar la petición con la e.firma proporcionada.", ex);
        }
    }

    /**
     * Firma el {@code wsu:Timestamp} (elemento {@code timestamp}, con
     * {@code Id=timestampId}) del header WS-Security de Autenticación. El
     * KeyInfo no lleva el certificado embebido: referencia, vía
     * SecurityTokenReference, al BinarySecurityToken ({@code bstId}) que ya
     * va en el mismo header. El &lt;ds:Signature&gt; resultante se agrega
     * como último hijo de {@code security} (el nodo wsse:Security).
     */
    public static void firmarTimestampWsSecurity(Document doc, Element security, Element timestamp, String timestampId,
            String bstId, PrivateKey llave) throws SatDescargaMasivaException {
        try {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            List<Transform> transforms = List.of(
                    fac.newTransform(CanonicalizationMethod.EXCLUSIVE, (TransformParameterSpec) null));

            Reference ref = fac.newReference("#" + timestampId, fac.newDigestMethod(DigestMethod.SHA1, null), transforms, null, null);

            SignedInfo signedInfo = fac.newSignedInfo(
                    fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(RSA_SHA1, null),
                    Collections.singletonList(ref));

            Element str = doc.createElementNS(WSSE_NS, "wsse:SecurityTokenReference");
            Element strRef = doc.createElementNS(WSSE_NS, "wsse:Reference");
            strRef.setAttribute("URI", "#" + bstId);
            strRef.setAttribute("ValueType", X509_TOKEN_VALUETYPE);
            str.appendChild(strRef);

            KeyInfoFactory kif = fac.getKeyInfoFactory();
            KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(new DOMStructure(str)));

            XMLSignature signature = fac.newXMLSignature(signedInfo, keyInfo);

            DOMSignContext signContext = new DOMSignContext(llave, security);
            signContext.setDefaultNamespacePrefix("ds");
            signContext.setIdAttributeNS(timestamp, null, "Id");

            signature.sign(signContext);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | MarshalException | XMLSignatureException ex) {
            throw new SatDescargaMasivaException("No se pudo firmar el header de autenticación con la e.firma proporcionada.", ex);
        }
    }
}
