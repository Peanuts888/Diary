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

import com.example.demo.form.LikeForm;
import com.example.demo.model.Article;
import com.example.demo.model.Likes;
import com.example.demo.service.LikeService;

@RestController
@RequestMapping("like")
public class LikeController {
	
	@Autowired
	LikeService service;
	
	@GetMapping("/count/{articleId}")
	public long count(@PathVariable("articleId") Integer articleId) {
		Article id = new Article();
		id.setId(articleId);
		return service.likeCount(id);
	}

	public long count(Article articleId) {
		return service.likeCount(articleId);
	}
	
	@PostMapping("/change")
	@ResponseStatus(HttpStatus.CREATED)
	public long change(@RequestBody LikeForm likeform) {
		Article articleId = new Article();
		Integer userId = likeform.getUserId();
		articleId.setId(likeform.getArticleId());
		
		if(state(articleId, userId) == 0) {
			Likes like = new Likes();
			like = likeform.toEntity();
			
			service.save(like);
		} else {
			delete(articleId, userId);
		}
		return count(articleId);
	}

	public long state(Article articleId, Integer userId) {
		return service.likeState(articleId, userId);
	}
	
	public void delete(Article articleId, Integer userId) {
		Likes like = service.findOne(articleId, userId);
		Integer likeId = like.getId();
		
		service.delete(likeId);
	}
}
