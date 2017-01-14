package demo.shiro.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import demo.domain.user.User;
import demo.shiro.utils.ShiroSecurityUtils;

/**
 * Created by zhengjianhui on 17/1/1.
 *
 * 登入拦截器
 */
public class LoginFilter extends FormAuthenticationFilter {

    public LoginFilter() {
        this.setLoginUrl("/rest/login");
        this.setPasswordParam("password");
        this.setUsernameParam("username");
    }

    /**
     * 针对登入成功的处理方式
     * 
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 成功后输出对象
            User user = ShiroSecurityUtils.getUser();
            out.println(new Gson().toJson(user));

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }


        return Boolean.TRUE;
    }

    /**
     * 针对登入失败  AuthenticationException 异常，由realm 抛出
     * 
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(e.getMessage());

        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }

        return Boolean.FALSE;
    }


    /**
     * 处理登入
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // ajax 登入 或者 form 表单登入
        if ("application/json".equalsIgnoreCase(request.getContentType())) {// 不是ajax请求
            return  executeLoginToJson(request, response);
        } else {
            return executeLogin(request, response);
        }
    }


    /**
     * 解析 json 数据创建 token
     * @param request
     * @param response
     * @return
     */
    protected AuthenticationToken createTokenToJson(ServletRequest request, ServletResponse response) throws IOException {

        BufferedReader bf = request.getReader();
        String str = null;
        StringBuffer sb = new StringBuffer();
        while((str = bf.readLine()) != null) {
            sb.append(str);
        }

        JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();


        return this.createToken(username, password, request, response);
    }


    /**
     * 重写 登入 json 方式
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean executeLoginToJson(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = this.createTokenToJson(request, response);
        if(token == null) {
            String e1 = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
            throw new IllegalStateException(e1);
        } else {
            try {
                Subject e = this.getSubject(request, response);
                e.login(token);
                return this.onLoginSuccess(token, e, request, response);
            } catch (AuthenticationException var5) {
                return this.onLoginFailure(token, var5, request, response);
            }
        }
    }



}
