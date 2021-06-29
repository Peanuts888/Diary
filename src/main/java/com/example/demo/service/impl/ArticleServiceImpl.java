package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository repository;
	
	@Override
	public List<Article> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<Article> findAll(Sort sort) {
		return repository.findAll(sort);
	}
	
	@Override
	public List<Article> searchArticle(String param) {
		return repository.searchArticle(param);
	}

}
