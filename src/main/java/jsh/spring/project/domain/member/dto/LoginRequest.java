package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class LoginRequest {
	private String email;
	private String password;
	
	public LoginRequest() {
		
	}
	
	public LoginRequest(String email, String password) throws Exception {
		this.email = email;
		this.password = new PasswordSecurityUtil().encryptPassword(password);
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) throws Exception {
		this.password = new PasswordSecurityUtil().encryptPassword(password);
	}
	
}
