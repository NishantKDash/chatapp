package com.nishant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.models.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
 
	public UserEntity findUserByusername(String username);
}
