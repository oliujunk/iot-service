package oliujunk.iotservice.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import oliujunk.iotservice.entity.Response;
import oliujunk.iotservice.entity.User;
import oliujunk.iotservice.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Resource
    private UserService userService;

    @SaIgnore
    @PostMapping("/login")
    public Response login(@RequestBody LoginParam loginParam) {
        String username = loginParam.username;
        if (StringUtils.hasText(username)) {
            User user = userService.getUserByUsername(loginParam.username);
            if (user == null) {
                return Response.fail("用户不存在");
            } else {
                if (user.getPassword().equals(SaSecureUtil.md5(loginParam.password))) {
                    StpUtil.login(user.getId());
                    SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
                    return Response.success(0, "登录成功", saTokenInfo);
                } else {
                    return Response.fail("密码错误");
                }
            }
        } else {
            return Response.fail("登录失败");
        }
    }

    @PostMapping("/logout")
    public Response logout(@RequestHeader(name = "token") String token) {
        StpUtil.logoutByTokenValue(token);
        return Response.success("注销成功");
    }

    private record LoginParam(String username, String password) {
    }

}
