package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.example.demo.model.Likes;


public interface LikeService {
	
	/**
	 * User(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Likes> findAll();

	public List<Likes> findAll(Sort sort);
	
	/**
	 * ユーザー名に紐付くUser(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Likes findOne(Integer articleId);

}
