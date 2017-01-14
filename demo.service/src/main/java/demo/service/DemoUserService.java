package demo.service;

import demo.basicxception.DemoException;
import demo.domain.user.User;

/**
 * Created by zhengjianhui on 16/10/7.
 */
public interface DemoUserService {

    void signUp(User user) throws DemoException;

    User queryUserByLoginName(String loginName);

}
