package jsh.spring.project.domain.board.dto;

public class ArticleCreateRequest {
	public int id;
	public int memberId;
	public String category;
	public String title;
	public String content;
	
	public ArticleCreateRequest(String category, String title, String content) {
		this.category = category;
		this.title = title;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
}
