package com.nishant.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.dtos.ChatRequestDto;
import com.nishant.dtos.ChatResponseDto;
import com.nishant.dtos.GetChatDto;
import com.nishant.models.Chat;
import com.nishant.services.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/{id}")
	public ResponseEntity<GetChatDto> getChat(@PathVariable Long id)
	{
		GetChatDto dto = new GetChatDto();
		Chat chat =  chatService.getChat(id);
		dto.setChatName(chat.getName());
		dto.setId(chat.getId());
		return ResponseEntity.ok(dto);
	}
	
	 @PostMapping("/create")
	 public ResponseEntity<ChatResponseDto> createChat(@RequestBody ChatRequestDto requestdto)
	 {
		 ChatResponseDto responsedto = new ChatResponseDto();
		 responsedto.setAdmin(requestdto.getAdmin());
		 Chat chat = chatService.createChat(requestdto.getName() , requestdto.getUsernames());
		 responsedto.setGid(chat.getId());
		 return ResponseEntity.ok(responsedto);
	 }
	 
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> deleteChat(@PathVariable("id") Long id)
	 {
		 chatService.deleteChat(id);
		 return ResponseEntity.ok("The chat has been deleted");
				 
	 }

}
