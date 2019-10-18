package jsh.spring.project.domain.member.dto;

public class MemberPasswordChangeRequest {
	private String password;
	
	public MemberPasswordChangeRequest() {
		
	}
	
	public MemberPasswordChangeRequest(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
