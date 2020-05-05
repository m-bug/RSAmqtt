package ch.mbug.com;

import ch.mbug.com.util.EncryptionClient;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaClient implements EncryptionClient {

    public static final String ALGORITHM = "RSA";

    private PrivateKey privateKey;
    private PublicKey publicKey;

    /*
     * ctor for receiver
     */
    public RsaClient(PublicKey publicKey, PrivateKey privateKey) {
        setPublicKey(publicKey);
        setPrivateKey(privateKey);
    }

    /*
     * ctor for sender
     */
    public RsaClient(PublicKey publicKey) {
        setPublicKey(publicKey);
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override public byte[] encrypt(String message, PublicKey publicKey) {
        Cipher cipher = null;
        byte[] encryptMessage = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptMessage = cipher.doFinal(message.getBytes());
            ;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return encryptMessage;
    }

    @Override public String decrypt(byte[] encryptedMessage, PrivateKey privateKey) {
        Cipher cipher = null;
        byte[] decryptMessage = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptMessage = cipher.doFinal(encryptedMessage);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(decryptMessage);
    }


    /* get the public key from a file */
    public static PublicKey getPublicKeyFromFile(String filename) {

        byte[] keyBytes = new byte[0];
        PublicKey publicKey = null;
        try {
            keyBytes = Files.readAllBytes(Paths.get(filename));
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            System.out.println(spec.getFormat());
            publicKey = kf.generatePublic(spec);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    /* get the private key from a file */
    public static PrivateKey getPrivateKeyFromFile(String filename) {

        byte[] keyBytes = new byte[0];
        PrivateKey privateKey = null;
        try {
            keyBytes = Files.readAllBytes(Paths.get(filename));
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance(ALGORITHM);
            privateKey = kf.generatePrivate(spec);

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return privateKey;
    }

}
