package cc.caker.boot.service.impl;

import cc.caker.boot.service.SocketService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangyl
 * @date 2019/6/21
 * @description
 */
@Service
public class SocketServiceImpl implements SocketService {

    private final static Map<String, Session> SESSION_MAP = Maps.newConcurrentMap();

    @Override
    public void addSession(String user, Session session) {
        SESSION_MAP.putIfAbsent(user, session);
    }

    @Override
    public void removeSession(String user) {
        SESSION_MAP.remove(user);
    }

    @Override
    public void sendMessage(String user, String message) {
        SESSION_MAP.forEach((k, v) -> send(v, user + "：" + message));
    }

    private void send(Session session, String message) {
        if (Objects.isNull(session)) {
            return;
        }

        RemoteEndpoint.Async async = session.getAsyncRemote();
        async.sendText(message);
    }
}
