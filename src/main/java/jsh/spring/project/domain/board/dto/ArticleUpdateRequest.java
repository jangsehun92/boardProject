package jsh.spring.project.domain.board.dto;

public class ArticleUpdateRequest {
	public int id;
	public int memberId;
	public String title;
	public String content;
	
	public ArticleUpdateRequest(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
}
