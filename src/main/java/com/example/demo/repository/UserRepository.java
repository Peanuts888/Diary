package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Users;


public interface UserRepository extends JpaRepository<Users, Long> {
	
//	@Query("select count(e) from User e WHERE e.username = :username")
//	long countByUsername(@Param("username") String username);
	
	Users findByUsername(String username);
    boolean existsByUsername(String username);
}
