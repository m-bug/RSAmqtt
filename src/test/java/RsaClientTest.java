import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RsaClientTest {

    public static final String MESSAGE = "Ich bin eine Message";
    RsaClient client = new RsaClient();

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testEncrypt() {
        client.generateKeyPair();
        assertNotNull(client.getPublicKey());
        byte[] encryptedText = client.encrypt(MESSAGE, client.getPublicKey());
        assertNotEquals(MESSAGE, encryptedText);
    }

    @Test
    public void testDecrypt() {
        client.generateKeyPair();
        assertNotNull(client.getPublicKey());
        byte[] encryptedText = client.encrypt(MESSAGE, client.getPublicKey());
        assertNotEquals(MESSAGE, encryptedText);

        // test decryption
        String decryptedMessage = client.decrypt(encryptedText, client.getPrivateKey());
        assertNotNull(decryptedMessage);
        assertEquals(MESSAGE, decryptedMessage);
    }

}
