package com.youknowwho.androidmqttclient;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.youknowwho.androidmqttclient.databinding.FragmentFirstBinding;

import org.eclipse.paho.client.mqttv3.MqttException;

public class FirstFragment extends Fragment {
    MQTTClient client;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Connect to MQTT broker and subscribe.
        try {
            client = new MQTTClient("tcp://mqtt.fedcampus.eu.org", "0", "django/mqtt", "", "");
        } catch (MqttException e) {
            Log.w("Failed to create MQTT client", e);
        }

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(
                view1 -> {
                    String text = binding.etMain.getText().toString();
                    try {
                        client.publish(text);
                    } catch (MqttException e) {
                        Log.w("MQTT publish failure", e);
                    }
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
