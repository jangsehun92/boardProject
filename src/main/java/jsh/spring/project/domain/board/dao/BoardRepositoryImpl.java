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
	public int totalCount(String category) {
		return sqlSession.selectOne("boardMapper.totalCount",category);
	}
	
	@Override
	public int totalCount(int memberId) {
		return sqlSession.selectOne("boardMapper.totalCount_memberId",memberId);
	}

	@Override
	public List<Article> articleList(Map<String, Object> paramMap) {
		if(paramMap.get("memberId") != null) {
			return sqlSession.selectList("boardMapper.list_memberId", paramMap);
		}
		return sqlSession.selectList("boardMapper.list", paramMap);
	}

	
}
