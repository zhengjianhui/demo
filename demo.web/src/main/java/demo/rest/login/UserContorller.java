package demo.rest.login;

import demo.basicxception.DemoException;
import demo.shiro.utils.ShiroSecurityUtils;
import io.swagger.annotations.ApiParam;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.domain.user.User;
import demo.service.DemoUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by zhengjianhui on 16/10/7.
 */
@RestController
@Api("用户相关api")
public class UserContorller {

    @Autowired
    private DemoUserService demoUserService;


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ApiOperation("添加用户")
    public void addUser(@ApiParam(name = "user", value = "用户信息") @RequestBody User user) throws DemoException {

        demoUserService.signUp(user);
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ApiOperation("用户信息")
    public User userInfo() throws DemoException {

        return ShiroSecurityUtils.getUser();
    }

//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    @ApiOperation("退出登入")
//    public void logout() {
//
//        SecurityUtils.getSubject().logout();
//    }



//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ApiOperation("用户信息")
//    public User login() throws DemoException {
//
//        return new User();
//    }


}
