package jsh.spring.project.domain.member.dto;

public class MemberPasswordChangeRequest {
	private int number;
	private String password;
	
	public MemberPasswordChangeRequest() {
		
	}
	
	public MemberPasswordChangeRequest(int number, String password) {
		this.number = number;
		this.password = password;
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

	public void setPassword(String password) {
		this.password = password;
	}
	
}
