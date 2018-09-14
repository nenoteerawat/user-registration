package com.teerawat.registration.db.repositories;

import org.springframework.data.repository.CrudRepository;

import com.teerawat.registration.db.domains.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
}
