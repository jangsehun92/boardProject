package jsh.spring.project.domain.member.dto;

public class MemberProfileUpdateRequest {
	private int number;
	private String email;
	private String nickname;
	
	public MemberProfileUpdateRequest() {
		
	}
	
	public MemberProfileUpdateRequest(int number, String email, String nickname) {
		this.number = number;
		this.email = email;
		this.nickname = nickname;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
