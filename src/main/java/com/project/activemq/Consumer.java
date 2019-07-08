package com.project.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author yangcheng
 * @ClassName:
 * @Description:
 * @date 2019年06月14日 18:20
 */
@Component
public class Consumer {
    @JmsListener(destination = "msg")
    public void receiveMessage(String message){
        System.out.println(message);
    }
}
