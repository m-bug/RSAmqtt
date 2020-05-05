package ch.mbug.com;

import ch.mbug.com.util.MqttReceiver;

public class Receiver {

    public static final String PUBLIC = "keys/public_key.der";
    public static final String PRIVATE = "keys/private_key.der";

    public static void main(String[] args) {
        // create new client
        RsaClient client = new RsaClient(RsaClient.getPublicKeyFromFile(PUBLIC), RsaClient.getPrivateKeyFromFile(PRIVATE));

        // init mqtt client to receive messages
        MqttReceiver receiver = new MqttReceiver(client);
        receiver.connect();

    }

}
