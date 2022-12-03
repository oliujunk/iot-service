package oliujunk.iotservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("iot_user_tree")
public class UserTree {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;

    private Long ancestorId; // 父级用户ID

    private Integer level; // 层级

    private Long userId; // 用户ID
}
