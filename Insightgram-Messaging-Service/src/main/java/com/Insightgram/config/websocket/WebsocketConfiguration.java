package com.Insightgram.config.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Value("${allowed.frontend.origin}")
	private String allowedFrontendOrigin;
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
        .addEndpoint("/ws")
        .setAllowedOrigins(allowedFrontendOrigin)
        .withSockJS();
        
        registry.addEndpoint("/queue/user")
        .setAllowedOrigins(allowedFrontendOrigin)
        .withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	registry.enableSimpleBroker("/queue");
    	
    	registry.setApplicationDestinationPrefixes("/app");
    }

}
