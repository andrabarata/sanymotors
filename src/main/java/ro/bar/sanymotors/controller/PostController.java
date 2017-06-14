package ro.bar.sanymotors.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ro.bar.sanymotors.data.PropertiesData;
import ro.bar.sanymotors.service.PostService;
import ro.bar.sanymotors.session.PostData;

@Controller
@SessionAttributes("postData")
public class PostController {
	
	@Autowired
	private PropertiesData propertiesData;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/administration/changePage")
	public ModelAndView changePage(HttpSession session,
			@RequestParam(value = "page", required = true) String page) throws SQLException{
		Map<String, Object> modelMap = new HashMap<>();
		PropertiesData data = (PropertiesData) session.getAttribute("propertiesData");
		int pageValue = Integer.parseInt(page); 
		modelMap.put("page", pageValue);
		modelMap.put("announces", postService.getAllAnnounces(pageValue,Integer.parseInt(data.getPageItems())));
		return new ModelAndView("adminMainPanel",modelMap);
	}
	
	@RequestMapping(value="/administration/post")
	public ModelAndView managePost(
			@RequestParam(value="id", required = false) String elementId){
		
		return new ModelAndView("managePost");
	}
	@RequestMapping(value="/administration/props")
	public ModelAndView manageProps(
			@RequestParam(value="id", required = false) String elementId,
			@RequestParam(value="title", required = true) String title,
			@RequestParam(value="description", required = true) String description,
			@RequestParam(value="base64Content", required = true) String base64Content,
			@ModelAttribute("postData")PostData postData){
			
		postData.setTitle(title);
		postData.setDescription(description);
		postData.setElementId(elementId);
		postData.setImage(base64Content);
		return new ModelAndView("manageProps");
		
	}
	@RequestMapping(value="/administration/additImg")
	public ModelAndView manageAdditionalImages(
			@RequestParam(value="id", required = false) String elementId,
			@RequestParam(value="title", required = true) String title,
			@RequestParam(value="description", required = true) String description,
			@RequestParam(value="base64Content", required = true) String base64Content,
			@ModelAttribute("postData")PostData postData){
			
		postData.setTitle(title);
		postData.setDescription(description);
		postData.setElementId(elementId);
		postData.setImage(base64Content);
		return new ModelAndView("manageAdditImg");
		
	}
	
}
