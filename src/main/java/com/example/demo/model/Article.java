package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="article")
public class Article {

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "title", length = 50, nullable = false)
	private String title;
	
	@Column(name = "content", length = 1000, nullable = false)
	private String content;
	
	@Column(name = "created_date", nullable = false)
    private Timestamp createdDate;
	
	@Column(name = "updated_date", nullable = false)
    private Timestamp updatedDate;
	
	@Column(name = "user_id", nullable = false)
    private Integer userId;
	
}
