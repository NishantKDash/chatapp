package com.nishant.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends BaseModel{
  
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	
	private String message;
	
	private LocalDateTime timestamp;
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private Chat chat;
}
