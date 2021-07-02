package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.form.BookmarkForm;
import com.example.demo.model.Article;
import com.example.demo.model.Bookmark;
import com.example.demo.service.BookmarkService;

@RestController
@RequestMapping("bookmark")
public class BookmarkController {
	
	@Autowired
	BookmarkService service;
	
	@GetMapping("/count/{articleId}")
	public long count(@PathVariable("articleId") Integer articleId) {
		Article id = new Article();
		id.setId(articleId);
		return service.countBookmark(id);
	}

	private long count(Article articleId) {
		return service.countBookmark(articleId);
	}
	
	@PostMapping("/change")
	@ResponseStatus(HttpStatus.CREATED)
	public long change(@RequestBody BookmarkForm bookmarkform) {
		Article articleId = new Article();
		Integer userId = bookmarkform.getUserId();
		articleId.setId(bookmarkform.getArticleId());
		
		if(state(articleId, userId) == 0) {
			Bookmark bookmark = new Bookmark();
			bookmark = bookmarkform.toEntity();
			
			service.save(bookmark);
		} else {
			delete(articleId, userId);
		}
		return count(articleId);
	}

	public long state(Article articleId, Integer userId) {
		return service.getLikeState(articleId, userId);
	}
	
	public void delete(Article articleId, Integer userId) {
		Bookmark bookmark = service.findByArticleIdAndUserId(articleId, userId);
		Integer bookmarkId = bookmark.getId();
		
		service.delete(bookmarkId);
	}
}
