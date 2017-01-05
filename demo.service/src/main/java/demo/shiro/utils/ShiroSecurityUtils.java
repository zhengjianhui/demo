package demo.shiro.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.ognl.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import demo.domain.user.User;

/**
 * Created by zhengjianhui on 17/1/1.
 */
public class ShiroSecurityUtils {

    /**
     * 从shiro中取出用户
     * 
     * @return
     */
    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static User getTocken() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Subject getSubject() {
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }

    /**
     * 是否是Ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

}
