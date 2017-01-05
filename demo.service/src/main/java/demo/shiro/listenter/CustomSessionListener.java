package demo.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import demo.shiro.session.repository.ShiroSessionRepository;


/**
 * Created by zhengjianhui on 17/1/1.
 * 实现shiro 的 session 监听类
 * 当请求进入 shiro 的过滤链时自动进入
 */
public class CustomSessionListener implements SessionListener {

    private ShiroSessionRepository shiroSessionRepository;

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

    /**
     * 会话建立
     * @param session
     */
    @Override
    public void onStart(Session session) {

    }

    /**
     * 会话结束
     * @param session
     */
    @Override
    public void onStop(Session session) {

    }

    /**
     * 会话过期
     * @param session
     */
    @Override
    public void onExpiration(Session session) {

    }
}
