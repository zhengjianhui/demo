package demo.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import demo.basicxception.DemoException;
import demo.domain.user.User;
import demo.shiro.utils.ShiroSecurityUtils;

/**
 * Created by zhengjianhui on 17/1/2.
 */
public class RoleFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String[] arra = (String[]) mappedValue;

        User user = ShiroSecurityUtils.getUser();

        Subject subject = getSubject(request, response);
        for (String role : arra) {
            if (subject.hasRole(role)) {
                return Boolean.TRUE;
            }
        }

        throw new DemoException("权限不足");
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
            throw new DemoException("请先登入");
        } else {
            throw new DemoException("权限不足");
        }
    }
}
