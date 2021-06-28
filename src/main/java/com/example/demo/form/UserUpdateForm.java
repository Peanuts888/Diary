package com.example.demo.form;

import java.io.IOException;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.User;

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
	public UserUpdateForm(User user) {
		this.setDisplayName(user.getDisplayName());
		this.setProfile(user.getProfile());
		this.setLink(user.getLink());
	}
	
	/**
	 * Formの設定内容をUser Entityクラスに変換する.
	 *
	 * @return ユーザー情報(Entityクラス)
	 */
	public User toEntity() {

		User user = new User();
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
