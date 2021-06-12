package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Articles;

@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<Articles, Integer> {
	
	Articles getById(Integer id);
	@Query("select a from Articles a where a.title like %?1% or a.content like %?1% order by a.id desc")
	List<Articles> searchArticles(String param);
}