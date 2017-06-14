package ro.bar.sanymotors.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ro.bar.sanymotors.data.PropertiesData;
import ro.bar.sanymotors.session.PostData;

public class RequestInterceptor extends HandlerInterceptorAdapter {
		
	@Autowired
	private PropertiesData propertiesData;
	  
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession httpSession = request.getSession(true);
	    if (httpSession.getAttribute("propertiesData") == null)
	    	httpSession.setAttribute("propertiesData", propertiesData);
	    if (httpSession.getAttribute("postData") == null)
	    	httpSession.setAttribute("postData", new PostData());
	    return super.preHandle(request, response, handler);
	}
}
