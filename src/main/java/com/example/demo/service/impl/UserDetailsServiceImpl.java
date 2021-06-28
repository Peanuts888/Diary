package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.model.impl.UserDetailsImpl;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository repository;
	
	/**
	 * コンストラクタ.
	 *
	 * @param repository UserRepository
	 */
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	/**
	 * ユーザー名に紐づくユーザーの詳細情報を取得する.
	 *
	 * @param username ユーザー名
	 * @return ユーザーの詳細情報
	 */
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// ユーザー名が空か判定
		if (StringUtils.isEmpty(username)) {
			// 空の場合、UsernameNotFoundExceptionをthrowする
			throw new UsernameNotFoundException("Username is empty");
		}
		
		// ユーザー名に紐づく情報を取得
				User loginUser = repository.findByUsername(username);

				if (loginUser == null) {
					// ユーザー情報が空の場合、UsernameNotFoundExceptionをthrowする
					throw new UsernameNotFoundException("Not found Username : " + username);
				}
				// 権限情報
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

				// ユーザーの詳細情報のロールを権限情報に追加
				authorities.add(new SimpleGrantedAuthority(
						loginUser.getRole())
						);

				UserDetailsImpl user = new UserDetailsImpl(loginUser, authorities);

				return user;
			}
}
