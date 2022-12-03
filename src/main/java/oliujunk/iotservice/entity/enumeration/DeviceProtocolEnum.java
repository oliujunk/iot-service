package oliujunk.iotservice.entity.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeviceProtocolEnum {

    MQTT_JSON(0, "MQTT JSON协议"),
    MQTT_CUSTOM(1, "MQTT 自定义协议"),
    TCP_CUSTOM(2, "TCP 自定义协议"),
    ;

    @EnumValue
    @JsonValue
    private final Integer code;

    private final String msg;

    DeviceProtocolEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public static DeviceProtocolEnum getByCode(Integer code) {
        for (DeviceProtocolEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
