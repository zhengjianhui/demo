package demo.security.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import demo.security.authentication.token.MyUsernamePasswordAuthenticationToken;
import demo.security.domain.LoginInfo;

/**
 * Created by zhengjianhui on 16/10/7.
 * 用户登录认证过滤器
 */
public class UserAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public UserAuthenticationFilter() {
        // 默认登录地址
        super("/rest/login");
    }

    protected UserAuthenticationFilter(String filterProcessesUrl) {
        super(filterProcessesUrl);
    }

    protected UserAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        LoginInfo loginInfo = getLoginInfo(request);


        Authentication authRequest = null;


        // Allow subclasses to set the "details" property
        // 设置 Details 的属性

        // Details 请求细节类(保存请求细节  参数之类)
        // Called by a class when it wishes a new authentication details instance to be
        // AuthenticationDetailsSource 的 buildDetails() 意识为创建一个新的请求细节

        /*
         *      UsernamePasswordAuthenticationFilter  security 自带的login 拦截器
         *
         * 		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		 *
         *      源码中的片段 返回一个 Authentication 需要生成一个 继承于 UsernamePasswordAuthenticationToken 的实例
         */



        authRequest = new MyUsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword());

        if (authRequest instanceof AbstractAuthenticationToken) {
            // 像上造型，用于点出setDetails() 方法
            ((AbstractAuthenticationToken) authRequest).setDetails(authenticationDetailsSource.buildDetails(request));
        }

        // 返回一个Authentication 对象
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * 从request 中初始化自定义参数对象LoginInfo
     * @param request
     * @return
     */
    private LoginInfo getLoginInfo(HttpServletRequest request) {

        // 判断是否是json 请求
        LoginInfo loginInfo = null;
//
//            if (request.getContentType().contains("application/json")) {
//                String json = IOUtils.toString(request.getInputStream(), "utf-8");
//                loginInfo = JSONUtil.json2pojo(json, LoginInfo.class);
//            } else if (request.getContentType().contains("x-www-form-urlencoded")) {


        final Map<String, String[]> parameters = request.getParameterMap();
        loginInfo = new LoginInfo();
        loginInfo.setUsername(getParameterValue(parameters, "username"));
        loginInfo.setPassword(getParameterValue(parameters, "password"));
        String rememberMe = getParameterValue(parameters, "rememberMe");
//                if ("true".equals(rememberMe)) {
//                    loginInfo.setRememberMe(true);
//                }

        return loginInfo;

    }


    /**
     * 从request 中获取 指定的参数
     * @param parameters
     * @param name
     * @return
     */
    private static String getParameterValue(Map<String, String[]> parameters, String name) {
        String[] values = parameters.get(name);
        if (values != null && values.length > 0) {
            return values[0];
        } else {
            return null;
        }
    }
}
