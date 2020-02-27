package cbuc.homestay.listener;

import cbuc.homestay.config.SessionContext;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Explain: session监听器(用于监听session的创建销毁)
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/27
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        SessionContext.addSession(sessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        SessionContext.removeSession(sessionEvent.getSession());
    }
}
