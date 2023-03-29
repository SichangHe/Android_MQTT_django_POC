import paho.mqtt.client as mqtt
from django.conf import settings


def on_connect(mqtt_client: mqtt.Client, userdata, flags: dict, rc: int):
    """
    Callback for `client` when it connects.
    - `rc`: connection status code. 0 means success.
    """
    if rc == 0:
        print("Connected successfully")
        mqtt_client.subscribe("django/mqtt")
    else:
        print("Bad connection. Code:", rc)


def on_message(mqtt_client: mqtt.Client, userdata, msg: mqtt.MQTTMessage):
    """
    Callback for `client` when it receives a message.
    """
    print(f"Received message on topic: {msg.topic} with payload: {msg.payload}")


client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
client.username_pw_set(settings.MQTT_USER, settings.MQTT_PASSWORD)
client.connect(
    host=settings.MQTT_SERVER,
    port=settings.MQTT_PORT,
    keepalive=settings.MQTT_KEEPALIVE,
)
