package jsh.spring.project.domain.member.dto;

import jsh.spring.project.global.util.PasswordSecurityUtil;

public class MemberPasswordChangeRequest {
	private int number;
	private String password;
	private String newPassword;
	
	public MemberPasswordChangeRequest(String password, String newPassword) throws Exception {
		PasswordSecurityUtil passwordSecurityUtil = new PasswordSecurityUtil();
		this.password = passwordSecurityUtil.encryptPassword(password);
		this.newPassword = passwordSecurityUtil.encryptPassword(newPassword);
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
}
