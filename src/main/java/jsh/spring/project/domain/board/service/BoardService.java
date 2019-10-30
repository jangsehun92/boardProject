package jsh.spring.project.domain.board.service;

import java.util.List;

import jsh.spring.project.domain.board.domain.Article;

public interface BoardService {
	public int totalCount(String category);
	public int totalCount(int memberId);
	public List<Article> articleList(String category, int page, int countList);
	public List<Article> articleList( int memberId, int page, int countList);
	
}
