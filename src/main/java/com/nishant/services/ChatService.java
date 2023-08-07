package com.nishant.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.models.Chat;
import com.nishant.models.UserEntity;
import com.nishant.repository.ChatRepository;
import com.nishant.repository.UserRepository;

@Service
public class ChatService {
	
	
	@Autowired
	private ChatRepository chatrepo;
	@Autowired
	private UserService userservice;
	@Autowired
	private UserRepository userrepo;
	
	public Chat createChat(String name , List<String>participants)
	{
		List<UserEntity> users = userservice.getUsers();
		List<UserEntity> chatParticipants = participants.stream()
		            .map((username)->{
		            	for(int i = 0;i<users.size();i++)
		            	{
		            		if(users.get(i).getUsername().equals(username))
		            			return users.get(i);
		            	}
		            	return null;
		            })
		            .filter(user->{if(user == null)
		            	return false;
		            else
		            	return true;})
		            .collect(Collectors.toList());
		Chat chat = new Chat();
		chat.setName(name);
		chat.setUsers(chatParticipants);
		chatrepo.save(chat);
		chatParticipants.stream().forEach(user->{List<Chat> chats = user.getChats(); chats.add(chat);userrepo.save(user);});
		return chat;
		
		
		            
	}
	
	public void deleteChat(Long id)
	{
		Chat chat = chatrepo.findById(id).get();
		chat.getUsers().stream().forEach(user ->{List<Chat> chats = user.getChats(); 
		for(int i = 0;i<chats.size();i++)
		{
			if(chats.get(i).getId() == chat.getId())
				chats.remove(i);
			break;
		}
		userrepo.save(user);
			});
		chatrepo.deleteById(id);
	}
	
	public Chat getChat(Long id)
	{
		return chatrepo.findById(id).get();
	}
	


}
