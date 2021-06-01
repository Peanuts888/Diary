package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class Users {

//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//	
    @Id
	@NotBlank
	@Column(name = "username", length = 60, nullable = false)
    private String username;
	
	@NotBlank
	@Column(name = "password", length = 255, nullable = false)
    private String password;
	
	@Column(name = "created_date", nullable = false)
	private Timestamp createdDate;
	
	@Column(name = "role", length = 120, nullable = false)
	private String role;
	
	@Column(name = "display_name", length = 60, nullable = false)
	private String displayName;
	
	@Column(name = "profile", length = 150)
    private String profile;
	
	@Column(name = "link")
    private String link;
	
	@Column(name = "icon")
	private byte[] icon;
	
	@Column(name = "header_image")
	private byte[] headerImage;
	
	/** 有効フラグ. */
	@Column(name = "is_enabled", nullable = false)
	private boolean isEnabled;
}
