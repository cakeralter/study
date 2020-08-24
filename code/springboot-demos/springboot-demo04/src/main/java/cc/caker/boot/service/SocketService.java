package cc.caker.boot.service;

import javax.websocket.Session;

/**
 * @author wangyl
 * @date 2019/6/21
 * @description
 */
public interface SocketService {

    void addSession(String user, Session session);

    void removeSession(String user);

    void sendMessage(String user, String message);
}
