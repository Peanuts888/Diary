package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Users;

@RepositoryRestResource(collectionResourceRel = "loginUser", path = "loginUser")
public interface LoginUserRepository extends PagingAndSortingRepository<Users, String> {
	
//	List<Users> findByUsername(@Param("username") String username);

}