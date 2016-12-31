package demo.security.entrypoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhengjianhui on 16/10/6.
 *
 * rest 风格自定义异常 401 异常
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     *
     * AuthenticationException security 异常类 常见异常
     * UsernameNotFoundException 用户找不到
     * BadCredentialsException 坏的凭据（凭证无效）
     * AccountStatusException 用户状态异常它包含如下子类
     * AccountExpiredException 账户过期
     * LockedException账户锁定
     * DisabledException
     * 账户不可用CredentialsExpiredException证书过期
     *
     * 该处统一为 401 异常
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 抛出异常时统一处理为 401 异常
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
    }
}
