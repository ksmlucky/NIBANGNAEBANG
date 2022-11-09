package com.footprints.businessservice.app.domain.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹소켓 통신의 엔드포인트가 /ws-stomp 라면 stomp 통신임을 확인하고 SocketJS 연결
        registry.addEndpoint("/ws-stomp")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 구독(subscribe) url -> 수신할 때의 엔드포인트 /sub
        registry.enableSimpleBroker("/sub");
        // 메시지 발행(publish) url -> 송신할 때의 엔드포인트 /pub
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
