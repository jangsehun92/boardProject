package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class LoginRequest {
	private final String email;
	private final String password;
	
	public LoginRequest(String email, String password) throws Exception {
		this.email = email;
		this.password = new PasswordSecurityUtil().encryptPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
}
