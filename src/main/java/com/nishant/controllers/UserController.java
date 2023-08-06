package com.nishant.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.dtos.GetAllChatsDto;
import com.nishant.dtos.UserLoginRequestDto;
import com.nishant.dtos.UserLoginResponseDto;
import com.nishant.dtos.UserRegisterRequestDto;
import com.nishant.dtos.UserRegisterResponseDto;
import com.nishant.models.Chat;
import com.nishant.models.UserEntity;
import com.nishant.security.JwtConstant;
import com.nishant.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class UserController {
   
	
	private UserService userService;
	@Autowired
	private ModelMapper mapper;
	
	public UserController(UserService userService)
	{
		this.userService = userService;
	}
	@PostMapping("/register")
	public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto requestdto) throws Exception
	{
		try {
		UserEntity user = userService.createUser(requestdto);
		UserRegisterResponseDto responsedto = new UserRegisterResponseDto();
		mapper.map(user ,  responsedto);
		return ResponseEntity.ok(responsedto);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	
	@PostMapping("/verify")
	public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestdto) throws Exception
	{
		try {
			  UserEntity user = userService.verifyUser(requestdto);
			  UserLoginResponseDto responsedto = new UserLoginResponseDto();
			  mapper.map(user , responsedto);
			  
			  SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				String jwt = Jwts.builder()
				    .claim("username", requestdto.getUsername())
				    .claim("authorities", "normal_user")
				    .signWith(key)
				    .compact();
				responsedto.setToken(jwt);
			  return ResponseEntity.ok(responsedto);
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	@GetMapping("/api/getUsers")
	public ResponseEntity<List<String>> getUsers()
	{
		List<UserEntity> users= userService.getUsers();
		List<String> res = users.stream().map((user)->user.getUsername()).toList();
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("/api/{username}/chats")
	public ResponseEntity<GetAllChatsDto> getChatsForUser(@PathVariable String username)
	{
		GetAllChatsDto dto = new GetAllChatsDto();
		List<Chat> chats= userService.getChats(username);
		dto.setChatids(chats.stream().map(chat->chat.getId()).collect(Collectors.toList()));
		dto.setChatnames(chats.stream().map(chat->chat.getName()).collect(Collectors.toList()));
		dto.setUsername(username);
		return ResponseEntity.ok(dto);
	}
}
