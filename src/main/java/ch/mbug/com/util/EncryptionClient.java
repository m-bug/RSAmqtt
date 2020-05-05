package ch.mbug.com.util;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * A Java security Interface, in fact a Encryption Interface.
 *
 */
public interface EncryptionClient {
    /**
     * encrypts a message with public key and returns encrypted byte[]
     *
     * @param message  message to encrypt
     * @param publicKey public key to encrypt with
     * @return returns encrypted message as byte[]
     */
    byte[] encrypt(String message, PublicKey publicKey);

    /**
     * decrypts a message with private key and returns decrypted message as String
     *
     * @param encryptedMessage message to decrypt
     * @param privateKey private key to decrypt with
     * @return returns decrypted message as String
     */
    String decrypt(byte[] encryptedMessage, PrivateKey privateKey);


}

