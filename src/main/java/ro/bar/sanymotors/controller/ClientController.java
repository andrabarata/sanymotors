package ro.bar.sanymotors.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ro.bar.sanymotors.data.PropertiesData;

@Controller
public class ClientController {
	
	@Autowired
	private PropertiesData propertiesData;

	@RequestMapping(value="/")
	public ModelAndView initializeClientPlatform(HttpSession session){
		session.setAttribute("propertiesData", propertiesData);
		return new ModelAndView("clientHome");
	}
}
