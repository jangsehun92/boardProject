package jsh.spring.project.domain.member.domain;

public class MailForm {
	
	private String subject;
	private String content;
	private String email;
	private String authKey;
	private String form;
	private String to;
	
	public MailForm() {
		
	}
	
	public MailForm(String email, String authKey) {
		this.email = email;
		this.authKey = authKey;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAuthKey() {
		return authKey;
	}
	
	public String getForm() {
		return form;
	}
	
	public String getTo() {
		return to;
	}
	
	

}
