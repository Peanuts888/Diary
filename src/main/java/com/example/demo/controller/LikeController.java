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
import com.example.demo.model.Likes;
import com.example.demo.service.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {
	
	@Autowired
	LikeService service;
	
	@GetMapping("/state/{articleId}/{userId}")
	public long likeState(@PathVariable("articleId") String articleId, @PathVariable("userId") String userId) {
		return service.likeState(articleId, userId);
	}
	
	@PostMapping("/on")
	@ResponseStatus(HttpStatus.CREATED)
	public Likes likeOn(@RequestBody LikeForm likeform) {
		Likes like = new Likes();
		like = likeform.toEntity();
		return service.save(like);
	}

	@PostMapping("/off")
	@ResponseStatus(HttpStatus.CREATED)
	public void likeOff(Integer id) {
		service.delete(id);
	}
}
