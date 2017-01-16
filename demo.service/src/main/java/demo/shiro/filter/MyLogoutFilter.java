package demo.shiro.filter;

import java.io.Serializable;
import java.util.Deque;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import demo.domain.user.User;
import demo.shiro.cache.repository.JedisManager;
import demo.shiro.utils.SerializableUtils;

/**
 * Created by zhengjianhui on 17/1/16.
 */
public class MyLogoutFilter extends LogoutFilter {

    private JedisManager jedisManager;

    /**
     * 为了不和其他的缓存混淆，采用追加前缀方式以作区分
     */
    private static final String REDIS_SHIRO_DEQUE = "shiro-demo-deque:";
    /**
     * Redis 分片(分区)，也可以在配置文件中配置
     */
    private static final int DB_INDEX = 14;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 清除缓存
        Subject subject = this.getSubject(request, response);
        User user = (User) subject.getPrincipal();

        jedisManager.deleteByKey(DB_INDEX, SerializableUtils.serialize(REDIS_SHIRO_DEQUE + user.getUsername()));

        // 并退出
        subject.logout();

        // 输出退出成功响应
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_OK);
        return Boolean.FALSE;
    }

    public void setJedisManager(JedisManager jedisManager) {
        this.jedisManager = jedisManager;
    }
}
