package com.nishant.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
   
	@GetMapping("/")
	public ResponseEntity<String> welcome()
	{
		return ResponseEntity.ok("Welcome to chat api written in spring boot");
	}
	
	@GetMapping("/api/")
	public ResponseEntity<String> protectedwelcome()
	{
		return ResponseEntity.ok("Welcome to chat api , This url is accessible only to you");
	}
}
