package jsh.spring.project;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppApi {
	private static final Logger logger = LoggerFactory.getLogger(AppApi.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
		removeCategory(session);
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		removeCategory(session);
		return "memberPages/login";
	}
	
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public String join(HttpSession session) {
		removeCategory(session);
		return "memberPages/join";
	}
	
	public void removeCategory(HttpSession session) {
		String category = (String)session.getAttribute("category");
		if(category != null || !"".equals(category)) {
			session.removeAttribute("category");
		}
	}
}
