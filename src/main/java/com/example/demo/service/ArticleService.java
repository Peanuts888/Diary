package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.example.demo.model.Articles;


public interface ArticleService {
	
	public Articles getById(Integer articleId);
	
	public List<Articles> searchArticles(String param);
	
	/**
	 * User(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Articles> findAll();

	public List<Articles> findAll(Sort sort);
	
	/**
	 * ユーザー名に紐付くUser(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Articles findOne(Integer articleId);

}
