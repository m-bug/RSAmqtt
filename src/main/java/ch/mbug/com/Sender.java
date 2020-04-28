package ch.mbug.com;

import ch.mbug.com.util.MqttReceiver;
import ch.mbug.com.util.MqttSender;

public class Sender {

    public static final String MESSAGE = "Ich bin eine Message";
    public static final String PUBLIC = "public.key";
    public static final String PRIVATE = "private.key";

    public static void main(String[] args) {

        // create new client
        RsaClient client = new RsaClient(RsaClient.getPublicKeyFromFile(PUBLIC),RsaClient.getPrivateKeyFromFile(PRIVATE));

        // init mqtt client to publish messages
        MqttSender sender = new MqttSender();
        // encrypt message with private key
        byte[] encryptedText = client.encrypt(MESSAGE, client.getPublicKey());

        // send encrypted message
        sender.connect();
        sender.publish(encryptedText);
        sender.disconnect();

    }

}
