package com.nishant.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SocketMessageController {
	
	
	@Autowired
	private SocketMessageService socketservice;
	
	@MessageMapping("/socket_message/{id}")
	@SendTo("/chat/{id}")
	public SocketMessage message(@RequestBody SocketMessage message , @DestinationVariable("id") Long gid)
	{
		socketservice.saveMessage(message , gid);
		return message;
	}

}
