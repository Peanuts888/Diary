package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Article;

@Repository
@Transactional
@RepositoryRestResource(collectionResourceRel = "article", path = "article")
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	@Query("select a from Article a where a.title like %?1% or a.content like %?1% order by a.id desc")
	List<Article> searchArticle(String param);
	
}