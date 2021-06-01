package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.model.Articles;

@RepositoryRestResource(collectionResourceRel = "article", path = "article")
public interface ArticleRestRepository extends PagingAndSortingRepository<Articles, Integer> {
	
//	List<Articles> findby();
	
} 
