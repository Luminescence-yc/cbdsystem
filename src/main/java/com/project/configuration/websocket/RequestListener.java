package com.project.configuration.websocket;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Component
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpSession session = ((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();

    }
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }


}
