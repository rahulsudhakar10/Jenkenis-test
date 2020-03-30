package com.hibernate.mapping.repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.mapping.model.User;

public interface UserRepoistory extends JpaRepository<User, Long>  {
	
	Optional<User> findByEmail(String emailId);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
