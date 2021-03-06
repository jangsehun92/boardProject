package jsh.spring.project.domain.board.dao;

import java.util.List;
import java.util.Map;

import jsh.spring.project.domain.board.domain.Article;

public interface BoardRepository {
	public int totalCount(Map<String,Object> countParamMap);
	public List<Article> articleList(Map<String, Object> paramMap);
	
}
