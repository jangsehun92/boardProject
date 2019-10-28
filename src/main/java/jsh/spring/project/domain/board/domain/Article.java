package jsh.spring.project.domain.board.domain;

import java.sql.Date;

public class Article {
	public int id;
	public int memberId;
	public String title;
	public String writer;
	public String content;
	public Date regDate;
	
	public Article() {
		
	}
	
	public Article(int id, int memberId, String title, String writer, String content, Date regDate) {
		this.id = id;
		this.memberId = memberId;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
	}
	
	
}
