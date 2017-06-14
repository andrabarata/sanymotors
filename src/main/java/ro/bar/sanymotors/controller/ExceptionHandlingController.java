package ro.bar.sanymotors.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandlingController {
  
	@ExceptionHandler({Exception.class})
	public ModelAndView handleError(HttpSession session, Exception exception) {
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("error", "A intervenit o eroare.");
		if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
			return new ModelAndView("adminLogin", modelMap);
		}
		return new ModelAndView("clientPanel", modelMap);
	}
}