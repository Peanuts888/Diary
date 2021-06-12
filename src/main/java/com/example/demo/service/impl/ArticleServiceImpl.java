package com.example.demo.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Articles;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	/**
	 * Article(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private ArticleRepository repository;
	
	public Articles getById(Integer articleId) {
		return repository.getById(articleId);
	}
	
	public List<Articles> searchArticles(String param) {
		return repository.searchArticles(param);
	}

	/**
	 * Article(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Articles> findAll() {
		return repository.findAll();
	}

	/**
	 * ユーザー名に紐付くArticle(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Articles findOne(Integer articleId) {
		return repository.getOne(articleId);
	}

}
