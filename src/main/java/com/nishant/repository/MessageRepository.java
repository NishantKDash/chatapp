package com.nishant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.models.Chat;
import com.nishant.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long>{
   public List<Message>getMessagesByChat(Chat chat);
}
