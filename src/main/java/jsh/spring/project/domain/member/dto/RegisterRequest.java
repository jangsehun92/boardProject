package jsh.spring.project.domain.member.dto;

public class RegisterRequest {
	private String member_id;
	private String member_pw;
	private String member_email;
	private String member_nickName;
	private String member_authKey;
	//sns 회원가입 추가 시 추가적으로 변수 선언
	
	public RegisterRequest() {
		
	}
	
	public RegisterRequest(String member_id, String member_pw, String member_email, String member_nickName) {
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_email = member_email;
		this.member_nickName = member_nickName;
	}
	//불필요한 getter,setter 제거하기
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_nickName() {
		return member_nickName;
	}

	public void setMember_nickName(String member_nickName) {
		this.member_nickName = member_nickName;
	}
	
	public String getMember_authKey() {
		return member_authKey;
	}
	
	public void setMember_authKey(String authKey) {
		this.member_authKey = authKey;
	}
	
	
}
