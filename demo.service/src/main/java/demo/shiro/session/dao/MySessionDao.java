package demo.shiro.session.dao;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import demo.shiro.session.repository.ShiroSessionRepository;

/**
 * Created by zhengjianhui on 17/1/2.
 *
 * doCreate/doUpdate/doDelete/doReadSession分别代表创建/修改/删除/读取会话；此处通过把会话序列化后存储
 */
public class MySessionDao extends AbstractSessionDAO {

    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 创建session
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 获取sessionId
        Serializable sessionId = generateSessionId(session);
        // 将sessionId 存入
        assignSessionId(session, sessionId);
        shiroSessionRepository.saveSession(session);
        return sessionId;
    }

    /**
     * 读取session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return shiroSessionRepository.getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        shiroSessionRepository.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        // TODO: 17/1/5  之后实现
        return null;
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }
}
