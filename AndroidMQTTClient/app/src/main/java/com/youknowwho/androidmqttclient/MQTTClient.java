package com.youknowwho.androidmqttclient;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTClient implements MqttCallback {
    MqttClient client;

    public MQTTClient(String brokerUrl, String clientId, String topic, String username, String password) throws MqttException {
        client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());
        client.setCallback(this);
        // MQTT connection options.
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{brokerUrl});
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        // Connect and subscribe.
        client.connect(options);
        client.subscribe(topic);
    }


    @Override
    public void connectionLost(Throwable cause) {
        Log.w("MQTT connection lost", cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload());
        Log.d("MQTT received", payload);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.i("MQTT delivery complete", token.toString());
    }
}
