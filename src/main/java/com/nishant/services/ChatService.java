package com.nishant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.models.Chat;
import com.nishant.models.Message;
import com.nishant.models.UserEntity;
import com.nishant.repository.ChatRepository;
import com.nishant.repository.MessageRepository;
import com.nishant.repository.UserRepository;

@Service
public class ChatService {
	
	
	@Autowired
	private ChatRepository chatrepo;
	@Autowired
	private UserService userservice;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private MessageRepository messagerepo;
	
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
        List<UserEntity> users = chat.getUsers();
        for(UserEntity user:users)
        {
        	List<Chat> chats = user.getChats();
        	chats.remove(chat);
        	userrepo.save(user);
        }
        chat.setUsers(new ArrayList<>());
        List<Message> messages = messagerepo.getMessagesByChat(chat);
        for(Message message:messages)
        {
        	messagerepo.delete(message);
        }
		chatrepo.deleteById(id);
	}
	
	public Chat getChat(Long id)
	{
		return chatrepo.findById(id).get();
	}
	
	public List<UserEntity> getUsers(Long id)
	{
		return chatrepo.findById(id).get().getUsers();
	}
	


}
