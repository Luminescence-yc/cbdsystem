package com.project.test;

import com.project.configuration.websocket.GetHttpSessionConfigurator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat/{name}",configurator = GetHttpSessionConfigurator.class)//标记此类为服务端
@Component
public class WebSocketTest {
    private static int onlineCount=0;
    private static Map<String,WebSocketTest> onlineUser=new ConcurrentHashMap<String, WebSocketTest>();
    private String name;
    private Session session;

    @OnOpen
    public void onOpen(@PathParam("name") String name, Session session, EndpointConfig config){
        this.session=session;
        this.name=name;
        //webSocketSet.add(this);//加入set中
        WebSocketTest.onlineUser.put(name, this);
        HttpSession httpsession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        for (String s : onlineUser.keySet()) {
            WebSocketTest webSocketTest = onlineUser.get(s);
            webSocketTest.session.getAsyncRemote().sendText(name+"加入了聊天室");
        }
        System.out.println(httpsession.getId());
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为"+getOnlineCount());
    }

    @OnClose
    public void onClose(){
        //webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息："+message);
//        群发消息
        for (String s : onlineUser.keySet()) {
            WebSocketTest webSocketTest = onlineUser.get(s);
            webSocketTest.session.getAsyncRemote().sendText(name+":"+message);
        }
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        System.out.println("发生错误！");
        throwable.printStackTrace();
    }


    //   下面是自定义的一些方法
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketTest.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketTest.onlineCount--;
    }

}
