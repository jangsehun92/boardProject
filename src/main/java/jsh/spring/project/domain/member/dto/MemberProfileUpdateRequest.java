package jsh.spring.project.domain.member.dto;

public class MemberProfileUpdateRequest {
	private int id;
	private String email;
	private String nickname;
	
	
	public MemberProfileUpdateRequest(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}
	
}
