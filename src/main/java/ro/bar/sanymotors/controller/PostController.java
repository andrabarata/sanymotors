package ro.bar.sanymotors.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ro.bar.sanymotors.data.PropertiesData;
import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.model.impl.PostImpl;
import ro.bar.sanymotors.service.GenericService;
import ro.bar.sanymotors.service.PostService;
import ro.bar.sanymotors.session.PostData;
import ro.bar.sanymotors.utils.DataUtils;


@Controller
@SessionAttributes({"postData", "propertiesData", "foundPost"})
public class PostController {
	
	@Autowired
	private GenericService genericService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"/administration/changePage"})
	public ModelAndView changePage(HttpSession session, @RequestParam(value="page", required=true) String page,
			@ModelAttribute("propertiesData") PropertiesData propertiesData) throws SQLException {
	    
		Map<String, Object> modelMap = new HashMap<>();
	    if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
	    	int pageValue = Integer.parseInt(page);
	    	modelMap.put("page", pageValue);
	    	modelMap.put("announces", postService.getAllAnnounces(pageValue, Integer.parseInt(propertiesData.getAdminPageItems())));
	    	return new ModelAndView("adminMainPanel", modelMap);
	    }
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
	  
	@ResponseBody
	@RequestMapping({"/administration/setId"})
	public void setId(@RequestParam(value="id", required=true) String elementId, @ModelAttribute("postData") PostData postData) {
		postData.setElementId(elementId);
	}
	  
	@RequestMapping({"/administration/post"})
	public ModelAndView managePost(HttpSession session, @RequestParam(value="backAction", required=false) String backAction, @ModelAttribute("postData") PostData postData) {
		if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
			if (StringUtils.isNotBlank(postData.getElementId())) {
				Post foundPost = (PostImpl) genericService.getEntity("PostImpl", postData.getElementId());
				session.setAttribute("foundPost", foundPost);
				DataUtils.setPostData(postData, (PostImpl)foundPost);
				postData.setCategory(postService.getBelongingCategoryId(Integer.parseInt(postData.getElementId())) + "");
			} else {
				session.setAttribute("foundPost", new PostImpl());
			}
			if (postData.getCategories() == null)
				postData.setCategories(postService.getAllCategories());
			return new ModelAndView("managePost");
		}
	    Map<String, Object> modelMap = new HashMap<>();
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
	  
	@RequestMapping({"/administration/additImg"})
	public ModelAndView manageAdditionalImages(HttpSession session, @RequestParam(value="id", required=false) String elementId,
			@RequestParam(value="title", required=false) String title,
			@RequestParam(value="description", required=false) String description,
			@RequestParam(value="base64Content", required=false) String base64Content,
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="price", required=false) String price,
			@RequestParam(value="currency", required=false) String currency,
			@RequestParam(value="backAction", required=false) String backAction,
			@ModelAttribute("postData") PostData postData,
			@ModelAttribute("foundPost") Post foundPost) {
		
	    if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
	    	if (((StringUtils.isNotBlank(base64Content)) || (StringUtils.isNotBlank(backAction))) && (StringUtils.isBlank(backAction))) {
	    		postData.setTitle(title);
	    		postData.setDescription(description);
	    		postData.setElementId(elementId);
	    		base64Content = base64Content.replace("&comma&", ",");
	    		postData.setImage(base64Content);
	    		postData.setCategory(category);
	    		postData.setCurrency(currency);
	    		postData.setPrice(price);
	    		if (StringUtils.isNotBlank(postData.getElementId()))
	    			postData.setAdditionalImages(postService.getAdditionalImages(Integer.parseInt(postData.getElementId())));
	    	}
	    	return new ModelAndView("manageAdditImg");
	    }
	    Map<String, Object> modelMap = new HashMap<>();
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
	  
	@RequestMapping({"/administration/props"})
	public ModelAndView manageProps(HttpSession session,
			@RequestParam(value="base64Content", required=true) String[] base64Content,
			@ModelAttribute("postData") PostData postData) {
		
		Map<String, Object> modelMap = new HashMap<>();
	    if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
	    	postData.setAdditionalImages(null);
	    	if ((base64Content != null) && (base64Content.length > 0)) {
	    		postData.setAdditionalImages(null);
	    		for (String base64Value : base64Content) {
	    			if (StringUtils.isNotBlank(base64Value))
	    				postData.addAdditionalImage(base64Value.replace("&comma&", ","));
	    		}
	    	}
	    	if (postData.getAvailableAttributes() == null) {
	    		postData.setAvailableAttributes(postService.getAvailableAttributeNames());
	    	}
	    	if (StringUtils.isNotBlank(postData.getElementId()))
	    		modelMap.put("attributes", postService.getAttributes(Integer.parseInt(postData.getElementId())));
	    	return new ModelAndView("manageProps", modelMap);
	    }
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
	  
	@ResponseBody
	@RequestMapping({"/administration/addName"})
	public void addAttributeName(@RequestParam(value="attributeName", required=true) String attributeName, @ModelAttribute("postData") PostData postData) {
		postData.addAvailableName(attributeName);
	}
	  
	@RequestMapping({"/administration/deletePost"})
	public ModelAndView deletePost(HttpSession session, @RequestParam(value="id", required=true) String elementId,
			@ModelAttribute("propertiesData") PropertiesData propertiesData) throws NumberFormatException, SQLException {
		
	    if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
	    	PostImpl post = (PostImpl) genericService.getEntity("PostImpl", elementId);
	    	genericService.removeEntity(post);
	    	Map<String, Object> modelMap = new HashMap<>();
	    	propertiesData.setLastPage(postService.getLastPage(Integer.parseInt(propertiesData.getAdminPageItems())));
	    	modelMap.put("page", Integer.valueOf(1));
	    	modelMap.put("announces", postService.getAllAnnounces(1, Integer.parseInt(propertiesData.getAdminPageItems())));
	    	modelMap.put("msg", "Anuntul a fost sters cu succes!");
	    	return new ModelAndView("adminMainPanel", modelMap);
	    }
	    Map<String, Object> modelMap = new HashMap<>();
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
}
