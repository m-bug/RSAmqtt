package ch.mbug.com;

import ch.mbug.com.util.MqttSender;

import java.util.concurrent.TimeUnit;

public class Sender {

    public static final int DELAY = 5;
    public static final String MESSAGE = "Ich bin eine Message";
    public static final String PUBLIC = "keys/public_key.der";

    public static void main(String[] args) {

        // create new client
        RsaClient client = new RsaClient(RsaClient.getPublicKeyFromFile(PUBLIC));

        // init mqtt client to publish messages
        MqttSender sender = new MqttSender();

        // connect to broker
        sender.connect();

        // make sure, connection is established
        try {
            TimeUnit.SECONDS.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // send encrypted message to broker
        for (int i = 0; i < 100; i++) {
            // encrypt message with private key
            byte[] encryptedText = client.encrypt(MESSAGE, client.getPublicKey());

            sender.publish(encryptedText);
            System.out.println(encryptedText.toString());

        }

        // work done - time to disconnect
        sender.disconnect();
    }

}
