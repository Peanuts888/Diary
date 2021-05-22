package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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

//	@Id
//    @Column(name = "id", length = 30, nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
	
	@Id
	@NotBlank
	@Column(name = "username", length = 60, nullable = false)
    private String username;
	
	@NotBlank
	@Column(name = "password", length = 255, nullable = false)
    private String password;
	
	@Column(name = "profile", length = 150)
    private String profile;
	
	@Column(name = "link")
    private String link;
	
	@Column(name = "create_date", nullable = false)
    private Timestamp createDate;
	
	@Column(name = "role", length = 120, nullable = false)
	private String role;
	
	/** 有効フラグ. */
	@Column(name = "is_enabled", nullable = false)
	private boolean isEnabled;
	
	@Column(name = "icon")
    private byte[] icon;
	
	@Column(name = "header_image")
    private byte[] headerImage;
}
