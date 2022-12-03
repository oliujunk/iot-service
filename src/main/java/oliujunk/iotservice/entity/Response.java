package oliujunk.iotservice.entity;

import lombok.Data;
import oliujunk.iotservice.entity.enumeration.ResponseCodeEnum;

@Data
public class Response {

    private Boolean success;
    private Integer code;
    private String msg;
    private Long timestamp = System.currentTimeMillis();
    private Object data;

    public Response(Integer code, String msg, Object data) {
        this.success = true;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response success(String msg) {
        return Response.success(0, msg, null);
    }

    public static Response success(String msg, Object data) {
        return Response.success(0, msg, data);
    }

    public static Response success(Object data) {
        return Response.success(0, "ok", data);
    }

    public static Response success(Integer code, String msg, Object data) {
        return new Response(code, msg, data);
    }

    public static Response fail() {
        return Response.fail(-1, null);
    }

    public static Response fail(String msg) {
        return Response.fail(-1, msg);
    }

    public static Response fail(Integer code, String msg) {
        Response response = new Response(code, msg, null);
        response.setSuccess(false);
        return response;
    }

    public static Response fail(ResponseCodeEnum err) {
        return Response.fail(err.getCode(), err.getMsg());
    }

}
