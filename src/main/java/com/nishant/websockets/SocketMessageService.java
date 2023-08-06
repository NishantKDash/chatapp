package com.nishant.websockets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.models.Message;
import com.nishant.repository.ChatRepository;
import com.nishant.repository.MessageRepository;
import com.nishant.repository.UserRepository;

@Service
public class SocketMessageService {
  
	@Autowired
	private MessageRepository messageRepo;
	@Autowired 
	UserRepository userRepo;
	@Autowired
	private ChatRepository chatRepo;
	
	public void saveMessage(SocketMessage message , Long id)
	{
		Message saveMessage = new Message();
		saveMessage.setMessage(message.getMessage());
		saveMessage.setUser(userRepo.findUserByusername(message.getUsername()));
		saveMessage.setChat(chatRepo.findById(id).get());
		messageRepo.save(saveMessage);
		
		
	}
}
