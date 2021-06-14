package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Follows;
import com.example.demo.repository.FollowRepository;
import com.example.demo.service.FollowService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class FollowServiceImpl implements FollowService {

	/**
	 * Article(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private FollowRepository repository;
	
	/**
	 * Article(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Follows> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Follows> findAll(Sort sort) {
		return repository.findAll(sort);
	}
		
	/**
	 * ユーザー名に紐付くArticle(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Follows findOne(Integer articleId) {
		return repository.getOne(articleId);
	}


}
