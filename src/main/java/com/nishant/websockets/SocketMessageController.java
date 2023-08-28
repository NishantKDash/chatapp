package com.nishant.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.TextMessage;

@Controller
public class SocketMessageController {
	
	
	@Autowired
	private SocketMessageService socketservice;
	
	@MessageMapping("/socket_message/{id}")
	@SendTo("/chat/{id}")
	public SocketMessage message(@RequestBody SocketMessage message , @DestinationVariable("id") Long gid)
	{
		//Destination variable is used for messagemapping
		socketservice.saveMessage(message , gid);
		return message;
	}
	
	@MessageMapping("/notification/{username}/{id}")
	@SendTo("/chat/notification/{username}")
	public SocketMessage_2 message(@RequestBody SocketMessage_2 message , @DestinationVariable("username") String username , @DestinationVariable("id") String gid)
	{
		return message;
	}
	
	@MessageMapping("/signal/{id}")
	@SendTo("/chat/signal/{id}")
	public String message(@RequestBody String message , @DestinationVariable("id") Long id)
	{
		return message;
	}

}
