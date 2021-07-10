package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.User;

public interface UserService {
	
	public Page<User> searchUser(String param, Pageable pageable);

	/**
	 * User(Entity)クラスのデータを全件取得する.
	 *
	 * @return userテーブルの全件データ
	 */
	public List<User> findAll();

	/**
	 * ユーザー名に紐付くUser(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public User findOne(String username);
	
	public User findOne(Integer id);

	/**
	 * User(Entity)クラスのデータを保存する.
	 *
	 * @param user User(Entity)クラス
	 * @return 保存したUser(Entity)クラス
	 */
	public User save(User user);

	public long countByUsername(String username);
	
}
