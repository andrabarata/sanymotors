package ro.bar.sanymotors.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ro.bar.sanymotors.data.PropertiesData;
import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.service.PostService;
import ro.bar.sanymotors.session.PostData;


@Controller
@SessionAttributes({"propertiesData", "postData"})
public class ClientController{
	
	  @Autowired
	  private PostService postService;
	  
	  public ClientController() {}
	  
	  @RequestMapping({"/"})
	  public ModelAndView initializeClientPlatform(HttpSession session) {
		  
		  Map<String, Object> modelMap = new HashMap<>();
		  Map<Integer, String> categories = postService.getAllCategories();
		  modelMap.put("categories", categories);
		  return new ModelAndView("clientHome", modelMap);
	  }
	  
	  @RequestMapping({"/motociclete"})
	  public ModelAndView getPosts(@RequestParam(value="category", required=false) String category,
			  @RequestParam(value="pagina", required=false) String page,
			  @ModelAttribute PropertiesData propertiesData, @ModelAttribute PostData postData) {
		  
		  Map<String, Object> modelMap = new HashMap<>();
		  int pageValue = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
		  modelMap.put("page", pageValue);
		  postData.setCategory(category);
		  propertiesData.setLastMotorcyclePage(postService.getLastMotorcyclePage(Integer.parseInt(propertiesData.getClientPageItems()), category));
		  List<Post> posts = postService.getAllMotorcycles(pageValue, Integer.parseInt(propertiesData.getClientPageItems()), category);
		  postData.setPosts(posts);
		  modelMap.put("posts", posts);
		  return new ModelAndView("clientPosts", modelMap);
	  }
	  
	  @RequestMapping({"/motociclete/anunt"})
	  public ModelAndView getPost(@RequestParam(value="p", required=true) String elementId,
			  @ModelAttribute PostData postData,
			  @ModelAttribute PropertiesData propertiesData) {
		  
		  if (postData.getPosts() == null) {
			  List<Post> posts = postService.getAllMotorcycles(-1, Integer.parseInt(propertiesData.getClientPageItems()), postData.getCategory());
			  postData.setPosts(posts);
		  }
		  String decodedElementId = new String(Base64.decodeBase64(elementId));
		  Post foundPost = postData.getPostById(decodedElementId);
		  Map<String, Object> modelMap = new HashMap<>();
		  modelMap.put("post", foundPost);
		  return new ModelAndView("clientPost", modelMap);
	  }
	  
	  @RequestMapping({"/piese"})
	  public ModelAndView getPieces(@RequestParam(value="pagina", required=false) String page,
			  @ModelAttribute PropertiesData propertiesData,
			  @ModelAttribute PostData postData) {
		  
		  Map<String, Object> modelMap = new HashMap<>();
		  int pageValue = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
		  modelMap.put("page", Integer.valueOf(pageValue));
		  propertiesData.setLastPiecesPage(postService.getLastPiecesPage(Integer.parseInt(propertiesData.getClientPageItems())));
		  List<Post> posts = postService.getAllPieces(pageValue, Integer.parseInt(propertiesData.getClientPageItems()));
		  postData.setPosts(posts);
		  modelMap.put("posts", posts);
		  return new ModelAndView("piese", modelMap);
	  }
	  @RequestMapping({"/inchirieri"})
	  public ModelAndView getRents(@RequestParam(value="pagina", required=false) String page,
			  @ModelAttribute PropertiesData propertiesData,
			  @ModelAttribute PostData postData) {
		  
		  Map<String, Object> modelMap = new HashMap<>();
		  int pageValue = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
		  modelMap.put("page", Integer.valueOf(pageValue));
		  propertiesData.setLastRentPage(postService.getLastRentPage(Integer.parseInt(propertiesData.getClientPageItems())));
		  List<Post> posts = postService.getAllRents(pageValue, Integer.parseInt(propertiesData.getClientPageItems()));
		  postData.setPosts(posts);
		  modelMap.put("posts", posts);
		  return new ModelAndView("inchirieri", modelMap);
	  }
	  
	  @RequestMapping({"/piese/anunt"})
	  public ModelAndView getPiesa(@RequestParam(value="p", required=true) String elementId, @ModelAttribute PostData postData) {
		  String decodedElementId = new String(Base64.decodeBase64(elementId));
		  Post foundPost = postData.getPostById(decodedElementId);
		  Map<String, Object> modelMap = new HashMap<>();
		  modelMap.put("post", foundPost);
		  return new ModelAndView("clientPiesa", modelMap);
	  }
	  
	  @RequestMapping({"/contact"})
	  public ModelAndView getContact() {
		  return new ModelAndView("contact");
	  }
	  
	  @RequestMapping({"/service"})
	  public ModelAndView getService() {
		  return new ModelAndView("service");
	  }
	  
	  @RequestMapping({"/forum"})
	  public ModelAndView getForum() {
		  return new ModelAndView("forum");
	  }
}
