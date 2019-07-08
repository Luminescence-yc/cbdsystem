package com.project.test;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangcheng
 * @ClassName: webScoket控制器
 * @Description: 用于消息提醒和统计当前人数
 * @date 2019年06月05日 13:21
 */
public class WebSocketController {
    //当前在线人数
    public static volatile int onlineCount = 0;
    //当前在线用户集合
    private static Map<String, WebSocketController> onlineUser = new ConcurrentHashMap<String, WebSocketController>();
    //当前用户session
    private Session session;
    //用户名
    private String name;

    @OnOpen
    public void onOpen(String name,Session session, EndpointConfig endpointConfig) {
        this.session = session;
        System.out.println(session.getId());
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        WebSocketController.onlineCount++;
    }

    @OnClose
    public void onClose(EndpointConfig endpointConfig) {
        WebSocketController.onlineCount--;
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }
    @OnError
    public void onError(Session session,Throwable throwable){
        System.out.println("发生错误！");
        throwable.printStackTrace();
    }
}
