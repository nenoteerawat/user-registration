package com.teerawat.registration.db.repositories;

import org.springframework.data.repository.CrudRepository;

import com.teerawat.registration.db.domains.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findByUsername(String username);
}
