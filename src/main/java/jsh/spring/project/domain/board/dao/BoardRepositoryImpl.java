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
		System.out.println(paramMap.get("memberId") + "로 articleList 가져오기");
		if(paramMap.get("memberId") != null) {
			System.out.println("boardRepository articleList");
			System.out.println("memberId : " + paramMap.get("memberId"));
			System.out.println("page : " + paramMap.get("page"));
			System.out.println("countList : " + paramMap.get("countList"));
			return sqlSession.selectList("boardMapper.list_memberId", paramMap);
		}
		return sqlSession.selectList("boardMapper.list", paramMap);
	}

	
}
