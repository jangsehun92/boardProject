package jsh.spring.project.domain.member.dto;

//수정필요
public class MemberPasswordChangeRequest {
	private int id;
	private String password;
	
	public MemberPasswordChangeRequest() {
		
	}
	
	public MemberPasswordChangeRequest(int id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
