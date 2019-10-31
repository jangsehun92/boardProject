package jsh.spring.project.domain.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jsh.spring.project.domain.board.service.BoardService;
import jsh.spring.project.domain.member.dao.MemberFindDao;
import jsh.spring.project.domain.member.domain.Member;
import jsh.spring.project.domain.member.dto.LoginRequest;
import jsh.spring.project.global.common.request.Pagination;

@Service
public class MemberSearchServiceImpl implements MemberSearchService{
	
	private final MemberFindDao memberFindDao;
	private final BoardService boardService;
	
	public MemberSearchServiceImpl(MemberFindDao MemberFindDao, BoardService boardService) {
		this.memberFindDao = MemberFindDao;
		this.boardService = boardService;
	}
	
	@Override
	public Member signIn(LoginRequest dto) {
		return memberFindDao.login(dto);
	}

	@Override
	public Map<String, Object> searchMember(int memberId, int page) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("countType", "id");
		paramMap.put("value", memberId);
		int totalCount = boardService.totalCount(paramMap);
		paramMap.clear();
		Pagination pagination = new Pagination(totalCount, page);
		
		paramMap.put("searchType", "id");
		paramMap.put("memberId", memberId);
		paramMap.put("page", page);
		paramMap.put("countList", pagination.getCountList());
		
		resultMap.put("memberResponse", memberFindDao.search(memberId));
		resultMap.put("articleList",boardService.articleList(paramMap));
		resultMap.put("pagination", pagination);
		return resultMap;
	}

}
