package oliujunk.iotservice.entity.enumeration;

public enum ResponseCodeEnum {
    SUCCESS(0, "请求成功"),
    OTHER_ERROR(-1, "其他错误"),
    PARAMETER_ERROR(1001, "参数错误"),
    NOT_FOUND(404, "未找到指定资源"),
    FORBIDDEN(403, "拒绝访问"),
    NOT_LOGIN(1002, "未登录"),
    NOT_ROLE(1003, "角色校验失败"),
    NOT_PERMISSION(1004, "权限校验失败"),
    DISABLE_SERVICE(1005, "账号被封禁"),
    ;

    private final Integer code;
    private final String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public static ResponseCodeEnum getByCode(Integer code) {
        for (ResponseCodeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
