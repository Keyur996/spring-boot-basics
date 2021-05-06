package com.km.pma.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.km.pma.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByName(String name);
	
}
