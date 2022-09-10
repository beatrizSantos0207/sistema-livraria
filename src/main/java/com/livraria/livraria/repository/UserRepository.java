package com.livraria.livraria.repository;

import com.livraria.livraria.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
	
}
