package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Likes;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class LikeServiceImpl implements LikeService {

	/**
	 * Article(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private LikeRepository repository;
	
	/**
	 * Article(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Likes> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Likes> findAll(Sort sort) {
		return repository.findAll(sort);
	}
		
	/**
	 * ユーザー名に紐付くArticle(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Likes findOne(Integer articleId) {
		return repository.getOne(articleId);
	}


}
