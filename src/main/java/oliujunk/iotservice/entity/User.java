package oliujunk.iotservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "iot_user", autoResultMap = true)
public class User {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String username; // 用户名

    @JsonIgnore
    private String password; // 密码

    private Boolean enabled; // 是否启用

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> roles; // 角色

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> permissions; // 权限

    private Long supervisorId; // 直属上级ID

    private String realName; // 真实姓名

    private String phone; // 联系电话

    private String email; // 邮箱

    private String remark; // 备注

    @TableField(exist = false)
    private List<UserTree> userTrees;

}
