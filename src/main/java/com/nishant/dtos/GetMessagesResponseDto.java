package com.nishant.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMessagesResponseDto {
   
	private String username;
	private String message;
	private LocalDateTime timestamp;
}
