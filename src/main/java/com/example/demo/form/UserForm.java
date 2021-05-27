package com.example.demo.form;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.example.demo.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank
	@Column(name = "username", length = 60, nullable = false)
    private String username;
	
	@Column(name = "icon")
    private byte[] icon;
	
	@Column(name = "header_image")
    private byte[] headerImage;
	
	@Column(name = "profile", length = 150)
    private String profile;
	
	@Column(name = "link")
    private String link;
	
	/**
	 * コンストラクタ.
	 */
	public UserForm() {

	}

	/**
	 * コンストラクタ.
	 * 
	 * @param user User(Entityクラス)
	 */
	public UserForm(Users user) {
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setIcon(user.getIcon());
		this.setHeaderImage(user.getHeaderImage());
		this.setProfile(user.getProfile());
		this.setLink(user.getLink());
	}
	
	/**
	 * Formの設定内容をUsers Entityクラスに変換する.
	 *
	 * @return ユーザー情報(Entityクラス)
	 */
	public Users toEntity() {

		Users user = new Users();
		user.setId(this.getId());
		user.setUsername(this.getUsername());
		user.setIcon(this.getIcon());
		user.setHeaderImage(this.getHeaderImage());
		user.setProfile(this.getProfile());
		user.setLink(this.getLink());

		return user;
	}
}
