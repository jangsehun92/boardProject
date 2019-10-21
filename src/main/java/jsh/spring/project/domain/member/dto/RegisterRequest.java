package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class RegisterRequest {
	private String email;
	private String password;
	private String nickname;
	
	public RegisterRequest(String email, String password, String nickname) throws Exception {
		this.email = email;
		this.password = new PasswordSecurityUtil().encryptPassword(password);
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

}
