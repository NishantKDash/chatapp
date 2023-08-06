package com.nishant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.models.Message;
import com.nishant.repository.MessageRepository;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messagerepo;
  
 
}
