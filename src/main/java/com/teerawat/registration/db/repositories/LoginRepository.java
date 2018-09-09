package com.teerawat.registration.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.teerawat.registration.db.entities.LoginEntity;

public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {
	LoginEntity findByUsername(@Nullable String username);
}
