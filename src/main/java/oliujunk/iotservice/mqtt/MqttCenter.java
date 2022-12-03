package oliujunk.iotservice.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Configuration
public class MqttCenter {

    @Resource
    private MqttProperties mqttProperties;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{String.format("tcp://%s:%d", mqttProperties.getHost(), mqttProperties.getPort())});
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientId() + "_consumer",
                mqttClientFactory(), "well_irrigation/up/+");
        adapter.addTopic("controller/up/+");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String payload = message.getPayload().toString();
            String topic = Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString();

            String[] topicArray = topic.split("/");
            String deviceType = topicArray[0];
            switch (deviceType) {
                case "well_irrigation" -> log.info("well_irrigation topic: {}, payload: {}", topic, payload); // 井灌计量
                case "LED" -> log.info("LED topic: {}, payload: {}", topic, payload);                         // LED
                case "controller" -> log.info("controller topic: {}, payload: {}", topic, payload);           // 常规控制器
            }
        };
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttProperties.getClientId() + "_producer", mqttClientFactory());
        messageHandler.setAsync(true);
        return messageHandler;
    }
}
