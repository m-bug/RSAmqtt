package ch.mbug.com.util;

import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.util.UUID;

/**
 * Diese Klasse implementiert das Interface MqttConnector.
 *
 */
public class MqttSender implements MqttConnector {

    Mqtt5AsyncClient CLIENT;

    public MqttSender() {
        CLIENT = Mqtt5Client.builder()
                .serverHost(MQTT_BROKER)
                .identifier(UUID.randomUUID().toString())
                .buildAsync();

    }

    @Override public void connect() {
        CLIENT.connect()
                .whenComplete((connAck, throwable) -> {
                    if (throwable != null) {
                        // Handle connection failure
                        System.out.println("connection fail");
                    } else {
                        System.out.println("Connection established");

                        // Setup score subscription
                        CLIENT.subscribeWith().topicFilter(MqttConnector.TOPIC_SCORE).send();
                    }
                });

    }

    @Override public void publish(byte[] bytes) {
        CLIENT.publishWith()
                .topic(TOPIC_SCORE)
                .payload(bytes)
                .send();
    }

    @Override public void disconnect() {
        CLIENT.disconnectWith()
                .sessionExpiryInterval(0)
                .send();
    }

}
