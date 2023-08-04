package com.nishant.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nishant.dtos.UserLoginRequestDto;
import com.nishant.dtos.UserRegisterRequestDto;
import com.nishant.exceptions.UserAlreadyExistsException;
import com.nishant.exceptions.UserDoesNotExistsException;
import com.nishant.exceptions.WrongUserNameOrPasswordException;
import com.nishant.models.UserEntity;
import com.nishant.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userrepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder encoder;
	
	public UserService(UserRepository userrepo)
	{
		this.userrepo = userrepo;
	}
	
	public UserEntity createUser(UserRegisterRequestDto requestdto) throws Exception
	{
		if(userrepo.findUserByusername(requestdto.getUsername()) != null)
			throw new UserAlreadyExistsException("The given username is already taken");
		
		UserEntity newuser = new UserEntity();
		mapper.map(requestdto, newuser);
		newuser.setPassword(encoder.encode(requestdto.getPassword()));
		userrepo.save(newuser);
	    return newuser;
	}
	
	
	public UserEntity verifyUser(UserLoginRequestDto requestdto) throws Exception
	{
		
		UserEntity user = userrepo.findUserByusername(requestdto.getUsername());
		if(user == null)
			throw new UserDoesNotExistsException("The given username does not exist");
		if(!encoder.matches(requestdto.getPassword(), user.getPassword()))
			throw new WrongUserNameOrPasswordException("The username or password is wrong");
	     return user;
		
	}
}
