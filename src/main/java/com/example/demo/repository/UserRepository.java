package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users getById(Integer id);
	Users findByUsername(String username);
	
}