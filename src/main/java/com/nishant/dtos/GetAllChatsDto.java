package com.nishant.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllChatsDto {
     
	private String username;
	private List<Long> Chatids;
	private List<String> Chatnames;
}
