package demo.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import java.io.IOException;

import demo.basicxception.DemoException;
import demo.shiro.utils.ShiroSecurityUtils;

/**
 * Created by zhengjianhui on 17/1/1.
 *
 * 登入拦截器
 */
public class LoginFilter  extends FormAuthenticationFilter {


    public LoginFilter() {
        this.setLoginUrl("/rest/login");
        this.setSuccessUrl("/rest/userInfo");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) {


        if(this.isLoginRequest(request, response)) {
            return Boolean.TRUE;
        } else {
            Subject subject = this.getSubject(request, response);
            return subject.getPrincipal() != null;
        }

    }



//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  {
//        //保存Request和Response 到登录后的链接
//        saveRequestAndRedirectToLogin(request, response);
//
//        return Boolean.FALSE;
//
//    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        // 保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(request, response);

        return Boolean.FALSE;
    }
}
