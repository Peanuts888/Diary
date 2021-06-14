package com.example.demo.form;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.annotation.FileRequired;
import com.example.demo.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String displayName;
	
    private String profile;
	
    private String link;
	
//	@FileRequired
	private MultipartFile icon;
	
//	@FileRequired
	private MultipartFile headerImage;
	
	
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
		this.setDisplayName(user.getDisplayName());
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
		user.setDisplayName(this.getDisplayName());
		user.setProfile(this.getProfile());
		user.setLink(this.getLink());
		try {
			user.setIcon(this.getIcon().getBytes());
			user.setHeaderImage(this.getHeaderImage().getBytes());
		} catch (IOException e) {}
		return user;
	}
	
}
