package com.example.demo.repository;

import java.sql.Timestamp;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Article;

@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	Page<Article> findByUserId(Integer userId, Pageable pageable);
	
	@Query("SELECT a FROM Article a INNER JOIN Likes l ON a.id = l.articleId WHERE l.userId = ?1")
	Page<Article> findArticleLiked(Integer userId, Pageable pageable);
	
	@Query("SELECT a FROM Article a WHERE a.title LIKE %?1% OR a.content LIKE %?1% ORDER BY a.id DESC")
	Page<Article> searchArticle(String param, Pageable pageable);

	@Query("SELECT a FROM Article a INNER JOIN Bookmark b ON a.id = b.articleId WHERE b.userId = ?1")
	Page<Article> findArticleBookmark(Integer userId, Pageable pageable);
	
	@Modifying(clearAutomatically=true, flushAutomatically=true)
	@Query("UPDATE Article a SET a.title = ?1, a.content = ?2, a.updatedDate = ?3 WHERE a.id = ?4")
	Integer updateArticle(String title, String content, Timestamp updatedDate, Integer id);
	
	void deleteById(Integer id);
}