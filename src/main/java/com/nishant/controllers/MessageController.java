package com.nishant.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.dtos.GetMessagesResponseDto;
import com.nishant.dtos.MessageCreateRequestDto;
import com.nishant.models.Message;
import com.nishant.services.MessageService;

@RestController
public class MessageController {
    
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping("/api/message/{id}")
	public ResponseEntity<List<GetMessagesResponseDto>>getMessages(@PathVariable Long id)
	{
		List<Message> messages = messageService.getMessage(id);
	    List<GetMessagesResponseDto> messagedtos = messages.stream().map(message->{
	    GetMessagesResponseDto dto = new GetMessagesResponseDto();
	    dto.setMessage(message.getMessage());
	    dto.setTimestamp(message.getTimestamp());
	    dto.setUsername(message.getUser().getUsername());
	    return dto;}).collect(Collectors.toList());
	    return ResponseEntity.ok(messagedtos);
	}
	
	@PostMapping("/api/message/{id}/create")
	public ResponseEntity<String> createMessage(@RequestBody MessageCreateRequestDto requestdto , @PathVariable Long id)
	{
		messageService.createMessage(id , requestdto);
		return ResponseEntity.ok("Message created");
	}
}
