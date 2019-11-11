package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class MemberPasswordChangeRequest {
	private int id;
	private String password;
	private String newPassword;
	
	public MemberPasswordChangeRequest() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = new PasswordSecurityUtil().encryptPassword(password);
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword)  throws Exception{
		this.newPassword = new PasswordSecurityUtil().encryptPassword(newPassword);
	}
	

	
}
