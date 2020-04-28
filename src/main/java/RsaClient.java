import util.EncryptionClient;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaClient implements EncryptionClient {

    public static final int KEY_SIZE = 2048;
    public static final String ALGORITHM = "RSA";
    public static final String FILENAME = "public.key";

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public PrivateKey getPrivateKey() {
        return this.privateKey;
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
            encryptMessage = cipher.doFinal(message.getBytes());;
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

    @Override public void generateKeyPair() {
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(KEY_SIZE);
            KeyPair pair = keyGen.generateKeyPair();
            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    /* save the public key in a file */
    public void exportPublicKey(){

        byte[] key = this.getPublicKey().getEncoded();
        Path path = Paths.get(FILENAME);
        // The method ensures that the file is closed when all bytes have been written ...
        try {
            Files.write(path, key);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static PublicKey getEncryptedPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }


}
