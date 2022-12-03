package oliujunk.iotservice.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String clientId;
}
