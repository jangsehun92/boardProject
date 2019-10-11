package jsh.spring.project.domain.member.dto;

public class RegisterConfirmRequest {
	private String email;
	private String authKey;
	
	public RegisterConfirmRequest() {
		
	}
	
	public RegisterConfirmRequest(String email, String authKey) {
		this.email = email;
		this.authKey = authKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
}
