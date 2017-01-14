package demo.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by zhengjianhui on 17/1/14.
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Boolean flag = super.doCredentialsMatch(token, info);



        return flag;
        // TODO 此处可配合 缓存 做登入次数验证
    }
}
