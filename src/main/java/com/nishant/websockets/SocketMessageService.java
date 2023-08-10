package com.nishant.websockets;

import java.time.LocalDateTime;

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
		System.out.println(message.getUsername() + "fkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjfkdjf");
		Message saveMessage = new Message();
		saveMessage.setMessage(message.getMessage());
		saveMessage.setUser(userRepo.findUserByusername(message.getUsername()));
		saveMessage.setChat(chatRepo.findById(id).get());
		saveMessage.setTimestamp(LocalDateTime.now());
		messageRepo.save(saveMessage);
		
		
	}
}
