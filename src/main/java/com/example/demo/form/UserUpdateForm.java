package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.example.demo.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String displayName;
	
    private String profile;
	
    private String link;
	
	private byte[] icon;
	
	private byte[] headerImage;
	
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
		this.setIcon(user.getIcon());
		this.setHeaderImage(user.getHeaderImage());
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
		user.setIcon(this.getIcon());
		user.setHeaderImage(this.getHeaderImage());

		return user;
	}
}
