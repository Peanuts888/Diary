package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User getById(Integer id);
	User findByUsername(String username);
	
	@Query("select u from User u where u.username like %?1% or u.displayName like %?1% or u.profile like %?1%")
	List<User> searchUser(String param);
	
	/**
	 * ユーザー名に紐付くUser(Entity)クラスの件数を取得.
	 *
	 * @param username ユーザー名
	 * @return 件数
	 */
	@Query("select count(e) from User e WHERE e.username = :username")
	long countByUsername(@Param("username") String username);
	
}