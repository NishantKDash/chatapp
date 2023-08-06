package com.nishant.dtos;

import java.util.List;

import com.nishant.models.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetChatDto {
  
	private Long id;
	private String chatName;
	private List<String> participants;
	private List<Message> messages;
}
