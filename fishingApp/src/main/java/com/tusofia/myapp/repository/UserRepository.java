package com.tusofia.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tusofia.myapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
 
	User findByEmail(String email);
	

	
}
