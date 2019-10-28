package jsh.spring.project.domain.board.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardApi {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardApi.class);
	
	//카테고리 별 게시판리스트(LIST)
	@RequestMapping(value = "{category}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable("category") String category, int page ) {
		return "";
	}

	//해당 게시글(READ)
	@RequestMapping(value = "{category}/{id}", method = RequestMethod.GET)
	public String detail() {
		return "";
	}
	
	//글작성(CREATE)
	@RequestMapping(value = "{category}", method = RequestMethod.POST)
	public String create() {
		return "";
	}
	
	//글수정(UPDATE)
	@RequestMapping(value = "{category}", method = RequestMethod.PUT)
	public String update() {
		return "";
	}
	
	//글삭제(DELETE)
	@RequestMapping(value = "{category}/{id}", method = RequestMethod.DELETE)
	public String delete() {
		return "";
	}
	
}
