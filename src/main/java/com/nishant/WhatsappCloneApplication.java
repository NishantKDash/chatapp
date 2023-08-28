package com.nishant;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nishant.controllers.UserController;
import com.nishant.dtos.UserRegisterRequestDto;

@SpringBootApplication
@Configuration
public class WhatsappCloneApplication {

	public static void main(String[] args) {
		ApplicationContext  context = SpringApplication.run(WhatsappCloneApplication.class, args);
		UserController usercontroller = context.getBean(UserController.class);
		try {
		usercontroller.register(new UserRegisterRequestDto("Nishant","nishant","1234"));}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}			
	}
	
	
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}



}
