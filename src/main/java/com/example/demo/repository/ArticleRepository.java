package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Article;

@Repository
@Transactional
@RepositoryRestResource(collectionResourceRel = "article", path = "article")
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	Page<Article> findAll(Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN Likes l ON a.id = l.articleId WHERE l.userId = ?1")
	Page<Article> findArticleLiked(Integer userId, Pageable pageable);
	
	@Query("SELECT a FROM Article a WHERE a.title LIKE %?1% OR a.content LIKE %?1% ORDER BY a.id DESC")
	List<Article> searchArticle(String param);

	@Query("SELECT a FROM Article a INNER JOIN Bookmark b ON a.id = b.articleId WHERE b.userId = ?1")
	Page<Article> findArticleBookmark(Integer userId, Pageable pageable);
	
	void deleteById(Integer id);
}