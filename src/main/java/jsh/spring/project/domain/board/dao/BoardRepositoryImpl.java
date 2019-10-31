package jsh.spring.project.domain.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jsh.spring.project.domain.board.domain.Article;

@Repository
public class BoardRepositoryImpl implements BoardRepository{
	
	private final SqlSession sqlSession;
	
	public BoardRepositoryImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int totalCount(Map<String,Object> countParamMap) {
		return sqlSession.selectOne("boardMapper.totalCount",countParamMap);
	}
	
	@Override
	public List<Article> articleList(Map<String, Object> paramMap) {
		return sqlSession.selectList("boardMapper.list", paramMap);
	}

	
}
