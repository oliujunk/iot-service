package oliujunk.iotservice.controller.exception;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import oliujunk.iotservice.entity.Response;
import oliujunk.iotservice.entity.enumeration.ResponseCodeEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Response handlerException(Exception exception) {
        Response response;
        switch (exception) {
            case NotLoginException notLoginException -> {
                log.info(notLoginException.getMessage());
                response = Response.fail(ResponseCodeEnum.NOT_LOGIN);
            }
            case NotRoleException notRoleException -> {
                log.info(notRoleException.getMessage());
                response = Response.fail(ResponseCodeEnum.NOT_ROLE);
            }
            case NotPermissionException notPermissionException -> {
                log.info(notPermissionException.getMessage());
                response = Response.fail(ResponseCodeEnum.NOT_PERMISSION);
            }
            case DisableServiceException disableLoginException -> {
                log.info(disableLoginException.getMessage());
                response = Response.fail(ResponseCodeEnum.DISABLE_SERVICE);
            }
            case IllegalArgumentException illegalArgumentException -> {
                log.info(illegalArgumentException.getMessage());
                response = Response.fail(illegalArgumentException.getMessage());
            }
            default -> {
                log.info(exception.getMessage());
                response = Response.fail(exception.getMessage());
            }
        }
        return response;
    }
}
