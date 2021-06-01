package com.example.demo.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

/**
 * UserEntityクラスを操作するServiceクラス.
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * User(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private UserRepository repository;

	/**
	 * PasswordEncoderクラス.
	 */
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * User(Entity)クラスのデータを全件取得する.
	 *
	 * @return usersテーブルの全件データ
	 */
	public List<Users> findAll() {
		return repository.findAll();
	}

	/**
	 * ユーザー名に紐付くUser(Entity)クラスのデータを1件取得する.
	 *
	 * @param username ユーザー名
	 * @return 該当した1件のデータ
	 */
	public Users findOne(String username) {
		return repository.getOne(username);
	}

	/**
	 * User(Entityクラス)のデータを保存する.
	 *
	 * @param user User(Entityクラス)
	 * @return 保存したUser(Entityクラス)
	 */
	@Transactional
	public Users insert(Users user) {

		// パスワード
		String password = user.getPassword();

		// パスワードが空か判定
		if (StringUtils.isBlank(password)) {
			// 空の場合

			// DBからパスワードを取得し、User(Entity)クラスのパスワードに設定
			Users dbData = this.findOne(user.getUsername());
			user.setPassword(dbData.getPassword());

		} else {
			// 空以外の場合

			// パスワードを暗号化し、User(Entity)クラスのパスワードに設定
			user.setPassword(passwordEncoder.encode(password));
		}

		// データベースにUsers(Entityクラス)を保存
		return repository.save(user);
	}
	
	public Users update(Users user) {
		return repository.save(user);
	}
}
