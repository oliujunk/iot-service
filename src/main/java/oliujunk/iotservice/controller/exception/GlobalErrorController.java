package oliujunk.iotservice.controller.exception;


import lombok.extern.slf4j.Slf4j;
import oliujunk.iotservice.entity.Response;
import oliujunk.iotservice.entity.enumeration.ResponseCodeEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(path = ERROR_PATH)
    public Response error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        log.error("错误代码: {}, 路径: {}", status, request.getRequestURI());
        ResponseCodeEnum err = ResponseCodeEnum.getByCode(status);
        assert err != null;
        return Response.fail(err.getCode(), err.getMsg());
    }
}
