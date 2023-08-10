package com.nishant.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.dtos.MessageCreateRequestDto;
import com.nishant.models.Chat;
import com.nishant.models.Message;
import com.nishant.repository.ChatRepository;
import com.nishant.repository.MessageRepository;
import com.nishant.repository.UserRepository;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messagerepo;
  
  @Autowired
  private ChatRepository chatrepo;
  
  @Autowired
  private UserRepository userrepo;
  
   public List<Message> getMessage(Long chatid)
   {
	   Chat chat = chatrepo.findById(chatid).get();
	   List<Message> messages= messagerepo.getMessagesByChat(chat);
	   return messages;
   }
   
   public Message createMessage(Long chatid , MessageCreateRequestDto requestDto)
   {
	   System.out.println("hdfjkdjfkdjfkdjfkdjfkdjf");
	   Message message = new Message();
	   message.setChat(chatrepo.findById(chatid).get());
	   message.setMessage(requestDto.getMessage());
	   message.setUser(userrepo.findUserByusername(requestDto.getUsername()));
	   message.setTimestamp(LocalDateTime.now());
	   return messagerepo.save(message);
   }
}
