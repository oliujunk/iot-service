package oliujunk.iotservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import oliujunk.iotservice.entity.enumeration.DeviceProtocolEnum;

import java.time.LocalDateTime;

@Data
@TableName(value = "iot_device", autoResultMap = true)
public class Device {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long creatorId;

    private String deviceSn;

    private String deviceName;

    private DeviceProtocolEnum protocol;

    private Float longitude;

    private Float latitude;

    private String remark;


}
