package com.nishant.dtos;

import lombok.Data;

@Data
public class UserLoginResponseDto {
  
	private String username;
	private String token;
}
