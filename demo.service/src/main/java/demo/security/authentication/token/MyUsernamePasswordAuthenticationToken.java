package demo.security.authentication.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by zhengjianhui on 16/10/7.
 *
 *
 * UsernamePasswordAuthenticationFilter  security 自带的login 拦截器
 *
 * UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
 *
 * 源码中的片段 返回一个 Authentication 需要生成一个 继承于 UsernamePasswordAuthenticationToken 的实例
 *
 * 自定义该类 可以 自定义属性用于security 传递 辅助验证
 */
public class MyUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public MyUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MyUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
