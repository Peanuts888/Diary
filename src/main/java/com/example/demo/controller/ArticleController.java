package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BookmarkService;
import com.example.demo.service.LikeService;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	LikeService likeService;
	
	@Autowired
	BookmarkService bookmarkService;

	@GetMapping("article/get{userId}")
	public List<Article> getArticles(@PathVariable Integer userId,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		
		return articlesPage.getContent();
	}
	
	@GetMapping("total-pages{userId}")
	public int getTotalPages(@PathVariable Integer userId,
			@PageableDefault(size = 6) Pageable pageable) {
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "");
		
		return page.getTotalPages();
	}
	
	@PostMapping("article/delete")
	@ResponseStatus(HttpStatus.CREATED)
	public long delete(@RequestBody Integer id) {
		Article articleId = new Article();
		articleId.setId(id);
		likeService.deleteByArticleId(articleId);
		bookmarkService.deleteByArticleId(articleId);
		articleService.delete(id);
		return 0;
	}
}
