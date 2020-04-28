package ch.mbug.com;

import ch.mbug.com.util.MqttReceiver;

public class Receiver {

    public static final String MESSAGE = "Ich bin eine Message";
    public static final String FILENAME = "public.key";

    public static void main(String[] args) {
        // create new client
        RsaClient client = new RsaClient(RsaClient.getPublicKeyFromFile(FILENAME));

        // init mqtt client to receive messages
        MqttReceiver receiver = new MqttReceiver(client);
        receiver.connect();


    }

}
