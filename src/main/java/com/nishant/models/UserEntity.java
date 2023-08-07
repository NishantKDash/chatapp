package com.nishant.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseModel{
	
	private String name;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
	private List<Chat> chats = new ArrayList<>();

}
