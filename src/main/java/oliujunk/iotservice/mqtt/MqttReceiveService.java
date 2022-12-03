package oliujunk.iotservice.mqtt;

import org.springframework.stereotype.Service;

@Service
public interface MqttReceiveService {
    void handlerMqttMessage(String topic, String payload);
}
