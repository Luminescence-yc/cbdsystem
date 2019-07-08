package com.project.configuration.websocket;

import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
@Configuration
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        StandardSessionFacade ssf  = (StandardSessionFacade) request.getHttpSession();
        if (ssf != null) {
            HttpSession session = (HttpSession) request.getHttpSession();
            sec.getUserProperties().put(HttpSession.class.getName(), session);
        }
        //sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        super.modifyHandshake(sec, request, response);
    }
}
