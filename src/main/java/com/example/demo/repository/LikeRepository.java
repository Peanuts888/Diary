package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Likes;

@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Likes, Integer> {
	
	
	
}