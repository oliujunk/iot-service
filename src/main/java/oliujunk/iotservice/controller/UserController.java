package oliujunk.iotservice.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import oliujunk.iotservice.entity.Response;
import oliujunk.iotservice.entity.User;
import oliujunk.iotservice.service.UserService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @SaCheckPermission(value = "user.get")
    @GetMapping("/users/{id}")
    public Response queryUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return Response.success(user);
    }

    @SaCheckPermission(value = "user.get")
    @GetMapping("/users")
    public Response queryUserByUsername(@RequestParam("username") String username) {
        User user = userService.getUserByUsername(username);
        return Response.success(user);
    }

    @SaCheckPermission(value = "user.add")
    @PostMapping("/users")
    public Response createUser(@RequestBody User user) {
        int result = userService.saveUser(user);
        Assert.isTrue(result == 1, "用户创建失败");

        return Response.success("用户创建成功");
    }

}
