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
public class UserUpdateForm {
	
	@Column(name = "username", length = 60, nullable = false)
    private String username;
//	
//	@Column(name = "password", length = 255, nullable = false)
//    private String password;

	@Column(name = "display_name", length = 60, nullable = false)
	private String displayName;
	
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
	public UserUpdateForm() {

	}

	/**
	 * コンストラクタ.
	 * 
	 * @param user User(Entityクラス)
	 */
	public UserUpdateForm(Users user) {
		this.setUsername(user.getUsername());
//		this.setPassword(user.getPassword());
		this.setDisplayName(user.getDisplayName());
		this.setProfile(user.getProfile());
		this.setLink(user.getLink());
//		this.setIcon(user.getIcon());
//		this.setHeaderImage(user.getHeaderImage());
	}
	
	/**
	 * Formの設定内容をUsers Entityクラスに変換する.
	 *
	 * @return ユーザー情報(Entityクラス)
	 */
	public Users toEntity() {

		Users user = new Users();
		user.setUsername(this.getUsername());
//		user.setPassword(this.getPassword());
		user.setDisplayName(this.getDisplayName());
		user.setProfile(this.getProfile());
		user.setLink(this.getLink());
//		user.setIcon(this.getIcon());
//		user.setHeaderImage(this.getHeaderImage());

		return user;
	}
}
