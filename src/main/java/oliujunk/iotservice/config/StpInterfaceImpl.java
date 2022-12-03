package oliujunk.iotservice.config;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import oliujunk.iotservice.entity.User;
import oliujunk.iotservice.entity.UserTree;
import oliujunk.iotservice.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        User user = userService.getUser(Long.valueOf((String) loginId));
        List<UserTree> userTrees = user.getUserTrees();
        List<String> permissions = userTrees.stream().map(i -> i.getUserId().toString()).collect(Collectors.toList());
        permissions.addAll(user.getPermissions());
        log.info(permissions.toString());
        return permissions;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getUser(Long.valueOf((String) loginId));
        return user.getRoles();
    }
}
