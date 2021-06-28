package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Like;
import com.example.demo.service.LikeService;

@RestController
public class LikeController {
	
	@Autowired
	LikeService service;
	
	@GetMapping("/like_state")
	public long likeState(@RequestParam int articleId, int userId) {
		return service.likeState(articleId, userId);
	}
	
	@PostMapping("/like_on")
	@ResponseStatus(HttpStatus.CREATED)
	public void likeOn(Like like) {
		service.save(like);
	}

	@PostMapping("/like_off")
	@ResponseStatus(HttpStatus.CREATED)
	public void likeOff(Integer id) {
		service.delete(id);
	}
}
