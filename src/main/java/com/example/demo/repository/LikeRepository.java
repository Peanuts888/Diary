package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Likes;

@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Likes, Integer> {
	
	@Query("select count(l) from Likes l where l.article_id = :articleId and l.user_id =:userId")
	long countByArticleIdAndUserId(@Param("articleId") long articleId,
								   @Param("userId") long userId);
	
	void deleteById(Integer id);
}