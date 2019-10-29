package jsh.spring.project.domain.board.service;

import java.util.HashMap;
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
	public int totalCount(String category) {
		return boardRepository.totalCount(category);
	}

	@Override
	public List<Article> articleList(String category, int page, int countList) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		paramMap.put("category", category);
		paramMap.put("page", page);
		paramMap.put("countList", countList);
		return boardRepository.articleList(paramMap);
	}

	

}
