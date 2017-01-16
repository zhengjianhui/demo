package demo.shiro.session.repository;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * Created by zhengjianhui on 17/1/1.
 */
public interface ShiroSessionRepository {

    /**
     * 存储Session
     * 
     * @param session
     */
    void saveSession(Session session);

    /**
     * 删除session
     * 
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);

    /**
     * 获取session
     * 
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);

    /**
     * 获取所有sessoin
     * 
     * @return
     */
    Collection<Session> getAllSessions();

}
