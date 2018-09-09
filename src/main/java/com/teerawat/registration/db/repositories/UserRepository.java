package com.teerawat.registration.db.repositories;

import org.springframework.data.repository.CrudRepository;

import com.teerawat.registration.db.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
}
