package cbuc.homestay.config;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Explain: 通过SessionId 获取指定 Session
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/27
 */
public class SessionContext {

    private static Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public static synchronized void removeSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
