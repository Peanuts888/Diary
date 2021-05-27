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
public class RegisterForm {

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@NotBlank
	@Column(name = "username", length = 60, nullable = false)
    private String username;
	
	@NotBlank
	@Column(name = "password", length = 255, nullable = false)
    private String password;
	
	/**
	 * Formの設定内容をUsers Entityクラスに変換する.
	 *
	 * @return ユーザー情報(Entityクラス)
	 */
	public Users toEntity() {

		Users user = new Users();
		user.setId(this.getId());
		user.setUsername(this.getUsername());
		user.setPassword(this.getPassword());

		return user;
	}
	
}
