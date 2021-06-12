package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Users;


public interface UserService {
	
//	public Users getById(Integer id);
	
	public List<Users> searchUsers(String param);

	/**
	 * User(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Users> findAll();

	/**
	 * ユーザー名に紐付くUser(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Users findOne(String username);

	/**
	 * User(Entity)クラスのデータを保存する.
	 *
	 * @param user User(Entity)クラス
	 * @return 保存したUser(Entity)クラス
	 */
	public Users save(Users user);
	
}
