package com.project.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月14日 18:19
 */
@Component
public class Productor {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息
     * @param destinationName 消息地址
     * @param message 消息内容
     */
    public void sendMessage(String destinationName,String message){
        System.out.println("=========>>向activemq消息队列发送消息"+"   "+message);
        Destination destination=new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination);
    }
}
