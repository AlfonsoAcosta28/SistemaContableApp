package com.mycompany.mavenproject1.sat.modelo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder;

/**
 * Credenciales de la e.firma (FIEL): certificado (.cer), llave privada
 * (.key, PKCS8 cifrado) y el RFC del titular, extraído del certificado.
 * El servicio de Descarga Masiva del SAT sólo acepta e.firma, no CSD.
 */
public class FielCredentials {

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    private static final Pattern RFC_UID_PATTERN
            = Pattern.compile("(?:OID\\.2\\.5\\.4\\.45|UID)=([^,]+)", Pattern.CASE_INSENSITIVE);

    private final X509Certificate certificate;
    private final PrivateKey privateKey;
    private final String rfc;

    private FielCredentials(X509Certificate certificate, PrivateKey privateKey, String rfc) {
        this.certificate = certificate;
        this.privateKey = privateKey;
        this.rfc = rfc;
    }

    public static FielCredentials cargar(File archivoCer, File archivoKey, char[] password) throws SatDescargaMasivaException {
        X509Certificate cert = cargarCertificado(archivoCer);
        PrivateKey key = cargarLlavePrivada(archivoKey, password);
        String rfc = extraerRfc(cert);
        return new FielCredentials(cert, key, rfc);
    }

    private static X509Certificate cargarCertificado(File archivoCer) throws SatDescargaMasivaException {
        try {
            byte[] bytes = Files.readAllBytes(archivoCer.toPath());
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(bytes));
        } catch (IOException | CertificateException ex) {
            throw new SatDescargaMasivaException(
                    "No se pudo leer el archivo .cer. Verifique que sea un certificado válido de la e.firma.", ex);
        }
    }

    private static PrivateKey cargarLlavePrivada(File archivoKey, char[] password) throws SatDescargaMasivaException {
        try {
            byte[] bytes = Files.readAllBytes(archivoKey.toPath());
            PKCS8EncryptedPrivateKeyInfo encryptedInfo = new PKCS8EncryptedPrivateKeyInfo(bytes);
            InputDecryptorProvider decryptorProvider = new JcePKCSPBEInputDecryptorProviderBuilder()
                    .setProvider(BouncyCastleProvider.PROVIDER_NAME)
                    .build(password);
            PrivateKeyInfo privateKeyInfo = encryptedInfo.decryptPrivateKeyInfo(decryptorProvider);
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME);
            return converter.getPrivateKey(privateKeyInfo);
        } catch (PKCSException ex) {
            throw new SatDescargaMasivaException("La contraseña de la llave privada (.key) es incorrecta.", ex);
        } catch (IOException ex) {
            throw new SatDescargaMasivaException(
                    "No se pudo leer el archivo .key. Verifique que sea la llave privada de la e.firma.", ex);
        }
    }

    private static String extraerRfc(X509Certificate cert) throws SatDescargaMasivaException {
        String subject = cert.getSubjectX500Principal().getName(X500Principal.RFC2253);
        Matcher matcher = RFC_UID_PATTERN.matcher(subject);
        if (matcher.find()) {
            String valor = matcher.group(1).trim();
            String rfc = valor.split("/")[0].trim();
            if (!rfc.isEmpty()) {
                return rfc.toUpperCase();
            }
        }
        throw new SatDescargaMasivaException(
                "No se pudo determinar el RFC a partir del certificado .cer. Verifique que sea una e.firma (FIEL) vigente.");
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String getRfc() {
        return rfc;
    }
}
