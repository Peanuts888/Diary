package com.example.demo.form;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.annotation.CustomCheck;
import com.example.demo.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm implements Serializable {
	
	/** シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;

	/** 正規表現(半角英数字). */
	private static final String ALPHANUMERIC_REGEXP = "[a-zA-Z0-9.]*";

	/** 正規表現(半角英数字)のエラーメッセージ. */
	private static final String ALPHANUMERIC_MESSAGE = "半角英字、数字、ピリオドを使用できます";

	@NotBlank
	@Size(max = 30)
	@Pattern(regexp = ALPHANUMERIC_REGEXP, message = ALPHANUMERIC_MESSAGE)
	@CustomCheck(uniqueUsername = "username", message = "既に登録されています")
    private String username;
	
	@NotBlank
	@Size(max = 255)
	@Pattern(regexp = ALPHANUMERIC_REGEXP, message = ALPHANUMERIC_MESSAGE)
    private String password;
	
	@Column(name = "display_name", length = 60, nullable = false)
	private String displayName;
	
	/**
	 * Formの設定内容をUser Entityクラスに変換する.
	 *
	 * @return ユーザー情報(Entityクラス)
	 */
	public User toEntity() {

		User user = new User();
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());
		user.setDisplayName(this.getUsername());

		return user;
	}
	
}
