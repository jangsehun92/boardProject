package jsh.spring.project.domain.board.service;

import java.util.Map;

import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;

public interface ArticleService {
	public Article getArticle(int id);
	public Map<String, Object> detail(int id);
	public int create(ArticleCreateRequest dto);
	public int update(ArticleUpdateRequest dto);
	public void delete(int id);
}
