package cc.caker.boot.controller;

import cc.caker.boot.component.CustomSpringConfigurator;
import cc.caker.boot.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author wangyl
 * @date 2019/6/21
 * @description
 */
@Component
@ServerEndpoint(value = "/socket/{user}", configurator = CustomSpringConfigurator.class)
public class SocketController {

    @Autowired
    private SocketService socketService;

    /**
     * 打开连接时执行
     *
     * @param user
     * @param session
     */
    @OnOpen
    public void open(@PathParam("user") String user, Session session) {
        socketService.addSession(user, session);
        socketService.sendMessage(user, "加入");
    }

    /**
     * 关闭连接时执行
     *
     * @param user
     */
    @OnClose
    public void close(@PathParam("user") String user) {
        socketService.removeSession(user);
        socketService.sendMessage(user, "退出");
    }

    /**
     * 连接出错时执行
     *
     * @param session
     * @param e
     */
    @OnError
    public void error(Session session, Throwable e) {
        e.printStackTrace();
    }

    /**
     * 接收消息时执行
     *
     * @param user
     * @param message
     */
    @OnMessage
    public void message(@PathParam("user") String user, String message) {
        socketService.sendMessage(user, message);
    }
}
