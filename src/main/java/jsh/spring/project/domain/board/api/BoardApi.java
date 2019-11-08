package jsh.spring.project.domain.board.api;

import java.util.HashMap;
import java.util.Map;

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
	
	//카테고리 별 게시판리스트(LIST) & 검색
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public String list(HttpSession session, Model model, @PathVariable("category") String category, @RequestParam(required = false, defaultValue="1")int page, @RequestParam(required = false)String query) {
		session.setAttribute("category", category);
		Map<String, Object> paramMap = new HashMap<String,Object>();
		Map<String, Object> countParamMap = new HashMap<String,Object>();
		
		if(query != null) {
			countParamMap.put("countType","query");
			countParamMap.put("value",query);
			paramMap.put("searchType","query");
			paramMap.put("query",query);
			model.addAttribute("query",query);
		}else {
			countParamMap.put("countType","category");
			countParamMap.put("value",category);
			paramMap.put("searchType", "category");
			paramMap.put("category",category);
		}
		
		int totalCount = boardService.totalCount(countParamMap);
		Pagination pagination = new Pagination(totalCount, page);
		int countList = pagination.getCountList();
		
		paramMap.put("page",page);
		paramMap.put("countList",countList);
		
		model.addAttribute("articleList", boardService.articleList(paramMap));
		model.addAttribute("pagination", pagination);
		return "boardPages/list";
	}
	
	@RequestMapping(value = "/{category}/create", method = RequestMethod.GET)
	public String create(Model model) {
		return "boardPages/create";
	}
}
