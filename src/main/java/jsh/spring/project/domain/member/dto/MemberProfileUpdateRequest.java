package jsh.spring.project.domain.member.dto;

//수정필요
public class MemberProfileUpdateRequest {
	private String email;
	private String nickname;
	
	public MemberProfileUpdateRequest() {
		
	}
	
	public MemberProfileUpdateRequest(String email, String nickname) {
		this.email = email;
		this.nickname = nickname;
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
