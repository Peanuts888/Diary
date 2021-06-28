package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Like;

@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	@Query("select count(l) from Like l where l.article_id = :articleId and l.user_id =:userId")
	long countByArticleIdAndUserId(int articleId, int userId);
	
	void deleteById(Integer id);
}