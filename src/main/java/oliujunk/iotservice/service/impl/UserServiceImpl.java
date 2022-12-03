package oliujunk.iotservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oliujunk.iotservice.entity.User;
import oliujunk.iotservice.entity.UserTree;
import oliujunk.iotservice.mapper.UserMapper;
import oliujunk.iotservice.mapper.UserTreeMapper;
import oliujunk.iotservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserTreeMapper userTreeMapper;

    @Override
    public User getUser(Long id) {
        User user = userMapper.selectById(id);
        Assert.notNull(user, "用户不存在");
        LambdaQueryWrapper<UserTree> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserTree::getAncestorId, id);
        List<UserTree> userTrees = userTreeMapper.selectList(wrapper);
        user.setUserTrees(userTrees);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(username), User::getUsername, username);
        User user = userMapper.selectOne(wrapper);

        LambdaQueryWrapper<UserTree> userTreeWrapper = new LambdaQueryWrapper<>();
        userTreeWrapper.eq(UserTree::getAncestorId, user.getId());
        List<UserTree> userTrees = userTreeMapper.selectList(userTreeWrapper);
        user.setUserTrees(userTrees);

        return user;
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer updateUser(Long id, User user) {
        user.setId(id);
        return userMapper.updateById(user);
    }

    @Override
    public Integer deleteUser(Long id) {
        return userMapper.deleteById(id);
    }
}
