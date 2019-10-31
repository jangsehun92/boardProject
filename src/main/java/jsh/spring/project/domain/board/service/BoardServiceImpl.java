package jsh.spring.project.domain.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.board.dao.BoardRepository;
import jsh.spring.project.domain.board.domain.Article;

@Service
public class BoardServiceImpl implements BoardService{
	
	private BoardRepository boardRepository;
	
	public BoardServiceImpl(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	@Override
	public int totalCount(Map<String,Object> countParamMap) {
		return boardRepository.totalCount(countParamMap);
	}
	
	public List<Article> articleList(Map<String,Object> paramMap) {
		return boardRepository.articleList(paramMap);
	}
	

}
