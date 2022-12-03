package oliujunk.iotservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oliujunk.iotservice.entity.User;

public interface UserService extends IService<User> {

    User getUser(Long id);

    User getUserByUsername(String username);

    Integer saveUser(User user);

    Integer updateUser(Long id, User user);

    Integer deleteUser(Long id);

}
