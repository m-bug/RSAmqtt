import ch.mbug.com.RsaClient;
import ch.mbug.com.util.MqttReceiver;
import ch.mbug.com.util.MqttSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RsaClientTest {

    public static final String MESSAGE = "Ich bin eine Message";
    public static final String PUBLIC = "keys/public_key.der";
    public static final String PRIVATE = "keys/private_key.der";

    // sender
    RsaClient senderClient = new RsaClient(RsaClient.getPublicKeyFromFile(PUBLIC));
    MqttSender sender = new MqttSender();

    // receiver
    RsaClient receiverClient = new RsaClient(RsaClient.getPublicKeyFromFile(PUBLIC), RsaClient.getPrivateKeyFromFile(PRIVATE));
    MqttReceiver receiver = new MqttReceiver(receiverClient);


    @BeforeEach
    void setUp() {

    }

    @Test
    public void testEncrypt() {
        byte[] encryptedText = senderClient.encrypt(MESSAGE, senderClient.getPublicKey());
        assertNotEquals(MESSAGE, encryptedText);
    }

    @Test
    public void testDecrypt() {
        byte[] encryptedText = receiverClient.encrypt(MESSAGE, receiverClient.getPublicKey());
        assertNotEquals(MESSAGE, encryptedText);

        // test decryption
        String decryptedMessage = receiverClient.decrypt(encryptedText, receiverClient.getPrivateKey());
        assertNotNull(decryptedMessage);
        assertEquals(MESSAGE, decryptedMessage);
    }

}
