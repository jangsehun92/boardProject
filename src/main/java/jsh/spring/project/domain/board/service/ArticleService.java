package jsh.spring.project.domain.board.service;

import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;

public interface ArticleService {
	public Article detail(int id);
	public int create(ArticleCreateRequest dto);
	public int update(ArticleUpdateRequest dto);
	public void delete(int id);
}
