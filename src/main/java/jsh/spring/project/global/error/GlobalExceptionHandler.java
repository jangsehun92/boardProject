package jsh.spring.project.global.error;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@RequestMapping("/{errorCode}")
	public String error(Model model, HttpServletRequest request, @PathVariable("errorCode") String errorCode) {
		String msg = (String)request.getAttribute("javax.servlet.error.message");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
		resultMap.put("REQUEST_URI",request.getAttribute("javax.servlet.error.request_uri"));
		resultMap.put("EXCEPTION_TYPE",request.getAttribute("javax.servlet.error.exception_type"));
		resultMap.put("EXCEPTION",request.getAttribute("javax.servlet.error.exception"));
		resultMap.put("SERVLET_NAME",request.getAttribute("javax.servlet.error.servlet_name"));
		
		try {
			int statusCode = Integer.parseInt(errorCode);
			
			switch(statusCode) {
				case 400: msg = "잘못된 요청입니다."; break;
				case 403: msg = "접근이 제한 되었습니다."; break;
				case 404: msg = "페이지를 찾을 수 없습니다."; break;
				case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
				case 500: msg = "서버에 오류가 발생하였습니다."; break;
				case 503: msg = "서비스를 사용할 수 없습니다."; break;
				default: msg = "알 수 없는 오류 발생하였습니다."; break;
			}
		}catch (Exception e) {
			msg = "기타 오류 발생하였습니다.";
		}finally {
			resultMap.put("MESSAGE",msg);
		}
		
		//logging
		if(resultMap.isEmpty() == false) {
			Iterator<Entry<String,Object>> iterator = resultMap.entrySet().iterator();
			Entry<String, Object> entry = null;
			
			while(iterator.hasNext()) {
				entry = iterator.next();
				logger.info("key : "+entry.getKey()+", value : "+entry.getValue());
			}
		}
		
		model.addAttribute("error", resultMap);
		return "commonPages/error";
	}

}
