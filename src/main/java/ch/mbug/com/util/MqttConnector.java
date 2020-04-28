package ch.mbug.com.util;

/**
 * Diese Interface stellt die Schnittstelle zum Mqtt Provider HiveMQ dar.
 *
 */
public interface MqttConnector {

    String MQTT_BROKER = "broker.hivemq.com";
    String TOPIC_SCORE = "RSA/test";

    /**
     * Diese Methode verbindet sich zum configurierten Broker und subscribed das Topic.
     *
     */
    void connect();

    /**
     * Diese Methode sendet eine Message an den Broker an ein bestimmtes Topic.
     *
     */
    void publish(byte[] bytes);

    /**
     * Diese Methode beendet die Connection zum Broker.
     *
     */
    void disconnect();


}
