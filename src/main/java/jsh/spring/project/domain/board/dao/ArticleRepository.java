package jsh.spring.project.domain.board.dao;

import java.util.Map;

import jsh.spring.project.domain.board.domain.Article;
import jsh.spring.project.domain.board.dto.ArticleCreateRequest;
import jsh.spring.project.domain.board.dto.ArticleUpdateRequest;

public interface ArticleRepository {
	public int viewCount(int id);
	public Article detail(int id);
	public int checkLike(Map<String, Integer> paramMap);
	public int create(ArticleCreateRequest dto);
	public int update(ArticleUpdateRequest dto);
	public int delete(int id);
}
