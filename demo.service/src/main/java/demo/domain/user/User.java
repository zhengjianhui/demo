package demo.domain.user;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Collection;
import java.util.Set;

public class User implements AuthorizationInfo {
    private Long id;

    private String username;

    private String loginname;

    private String password;

    protected Set<String> roles;

    protected Set<String> stringPermissions;

    protected Set<Permission> objectPermissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public Collection<String> getRoles() {
        return roles;
    }

    @Override
    public Collection<String> getStringPermissions() {
        return stringPermissions;
    }

    @Override
    public Collection<Permission> getObjectPermissions() {
        return objectPermissions;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

    public void setObjectPermissions(Set<Permission> objectPermissions) {
        this.objectPermissions = objectPermissions;
    }
}