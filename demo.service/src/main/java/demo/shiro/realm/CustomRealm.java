package demo.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import demo.domain.user.User;
import demo.service.DemoUserService;
import demo.shiro.utils.ShiroSecurityUtils;

/**
 * Created by zhengjianhui on 17/1/1.
 *
 * 继承 AuthorizingRealm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private DemoUserService demoUserService;

    // 盐 salt
    private static final String salt = "demo";

    /**
     * 理解为权限赋予
     * 
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // TODO: 17/1/1 权限与角色授予
        // 角色名的集合
        Set<String> roles = new HashSet<>();
        roles.add("user");
        // 权限名的集合
        Set<String> permissions = new HashSet<>();
        permissions.add("test");

        // ShiroSecurityUtils.getUser().setRoles(roles);

        // Iterator<Role> it = roleSet.iterator();
        // while(it.hasNext()){
        // roles.add(it.next().getName());
        // for(Permission per:it.next().getPermissionSet()){
        // permissions.add(per.getName());
        // }
        // }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;

    }

    /**
     * 理解为登入效验
     * 
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 从 shiro 的token 中获取 登入名
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
//        String password = String.valueOf(upToken.getPassword());

        User user = demoUserService.queryUserByLoginName(username);

        if(user == null) {
            throw new AuthenticationException("用户不存在");
        }
//        } else if (user.getPassword().equals(password)){
//            throw new AuthenticationException("密码错误");
//        }



//        User user = new User();
        Set<String> roles = new HashSet<>();
        roles.add("user");
        user.setRoles(roles);

//        throw new AuthenticationException("异常实验");

        /**
         * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
         * 将User 放入
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(salt),//salt
                getName());
        return info;
    }

    // @Override
    // public String getName() {
    // return getClass().getName();
    // }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
