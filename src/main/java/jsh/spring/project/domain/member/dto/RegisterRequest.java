package jsh.spring.project.domain.member.dto;

public class RegisterRequest {
	private String email;
	private String password;
	private String nickname;
	//sns 회원가입 추가 시 추가적으로 변수 선언
	
	public RegisterRequest() {
		
	}
	
	public RegisterRequest(String email, String password, String nickname) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
