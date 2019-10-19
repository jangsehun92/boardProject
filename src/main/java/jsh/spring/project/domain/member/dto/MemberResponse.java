package jsh.spring.project.domain.member.dto;

public class MemberResponse {
	private int number;
	private String email;
	private String nickname;
	private String status;
	
	public MemberResponse() {
		
	}
	
	public MemberResponse(int number, String email, String nickname, String status) {
		this.number = number;
		this.email = email;
		this.nickname = nickname;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
