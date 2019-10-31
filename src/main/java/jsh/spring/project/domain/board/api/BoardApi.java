package jsh.spring.project.domain.board.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jsh.spring.project.domain.board.service.BoardService;
import jsh.spring.project.global.common.request.Pagination;

@Controller
@RequestMapping("/articles")
public class BoardApi {
	private static final Logger logger = LoggerFactory.getLogger(BoardApi.class);
	
	private final BoardService boardService;
	
	public BoardApi(BoardService boardService){
		this.boardService = boardService;
	}
	
	//카테고리 별 게시판리스트(LIST)
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public String list(HttpSession session, Model model, @PathVariable("category") String category, @RequestParam(defaultValue="1")int page) {
		int totalCount = boardService.totalCount(category);
		Pagination pagination = new Pagination(totalCount, page);
		int countList = pagination.getCountList();
		session.setAttribute("category", category);
		model.addAttribute("pagination", pagination);
		model.addAttribute("articleList", boardService.articleList(category, page, countList));
		return "boardPages/list";
	}
	
	//글작성(CREATE) 요청
	@RequestMapping(value = "/{category}/create", method = RequestMethod.GET)
	public String create(Model model) {
		return "boardPages/create";
	}
	
	//글검색
}
