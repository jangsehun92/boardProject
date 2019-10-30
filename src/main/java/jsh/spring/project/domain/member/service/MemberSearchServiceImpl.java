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
		System.out.println("MemberSearchService searchMember");
		System.out.println("memberId : " + memberId + " page : " + page);
		int totalCount = boardService.totalCount(memberId);
		Pagination pagination = new Pagination(totalCount, page);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("memberResponse", memberFindDao.search(memberId));
		resultMap.put("articleList",boardService.articleList(memberId, page, pagination.getCountList()));
		return resultMap;
	}

}
