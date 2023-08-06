package com.nishant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long>{

}
