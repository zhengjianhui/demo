package demo.service.user.impl;


import java.util.List;
import java.util.Objects;

import demo.util.id.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import demo.basicxception.DemoException;
import demo.dao.mybatis.user.PermissionsMapper;
import demo.dao.mybatis.user.RoleMapper;
import demo.dao.mybatis.user.UserAuthorityMapper;
import demo.dao.mybatis.user.UserMapper;
import demo.domain.user.User;
import demo.service.DemoUserService;

/**
 * Created by zhengjianhui on 16/10/7.
 */
@Service
public class DemoUserServiceImpl implements DemoUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    @Autowired
    private PermissionsMapper permissionsMapper;

    private String passwordChaos = "123sadasdasasfwqrdsd1";

    @Override
    public void signUp(User user) throws DemoException {
        Assert.notNull(user, "用户信息不能为空");
        Assert.notNull(user.getLoginname(), "账号不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");
        Assert.notNull(user.getUsername(), "用户称号不能为空");

        User signUp = new User();
        signUp.setLoginname(user.getLoginname());

        List<User> getUser = userMapper.selective(signUp);

        if(getUser.size() > 0)
            throw new DemoException("账号已存在");

        signUp.setLoginname(null);
        signUp.setUsername(user.getUsername());
        if(userMapper.selective(signUp).size() > 0)
                throw new DemoException("用户名以存在");

        // 将密码混入md5 加密
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + passwordChaos).getBytes()));

        user.setId(IdUtil.getId());

        userMapper.insertSelective(user);
    }
}
