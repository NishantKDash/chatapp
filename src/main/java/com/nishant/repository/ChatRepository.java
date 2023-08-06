package com.nishant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.models.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long>{

}
