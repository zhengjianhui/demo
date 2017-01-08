package demo.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import demo.domain.user.User;
import demo.shiro.utils.ShiroSecurityUtils;

/**
 * Created by zhengjianhui on 17/1/2.
 */
public class RoleFilter extends AccessControlFilter {

    private static final String LOGIN_URL = "/rest/login";

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

        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {// 表示没有登录，重定向到登录页面
            saveRequest(request);
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_PAYMENT_REQUIRED);
        } else {
            // 否则返回401未授权状态码
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return Boolean.FALSE;
    }
}
