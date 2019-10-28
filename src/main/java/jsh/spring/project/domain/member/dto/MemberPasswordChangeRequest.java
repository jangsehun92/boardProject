package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class MemberPasswordChangeRequest {
	private int id;
	private String password;
	private String newPassword;
	
	public MemberPasswordChangeRequest(String password, String newPassword) throws Exception {
		PasswordSecurityUtil passwordSecurityUtil = new PasswordSecurityUtil();
		this.password = passwordSecurityUtil.encryptPassword(password);
		this.newPassword = passwordSecurityUtil.encryptPassword(newPassword);
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

	public String getNewPassword() {
		return newPassword;
	}
	
}
