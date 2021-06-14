package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Follows;

@Repository
@Transactional
public interface FollowRepository extends JpaRepository<Follows, Integer> {
	

	
}