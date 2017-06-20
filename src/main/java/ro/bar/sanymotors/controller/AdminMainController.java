package ro.bar.sanymotors.controller;

import com.mortennobel.imagescaling.AdvancedResizeOp.UnsharpenMask;
import com.mortennobel.imagescaling.ResampleOp;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.ByteArrayOutputStream;
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
import ro.bar.sanymotors.model.Attribute;
import ro.bar.sanymotors.model.Image;
import ro.bar.sanymotors.model.impl.AttributeImpl;
import ro.bar.sanymotors.model.impl.CategoryImpl;
import ro.bar.sanymotors.model.impl.ImageImpl;
import ro.bar.sanymotors.model.impl.PostImpl;
import ro.bar.sanymotors.service.GenericService;
import ro.bar.sanymotors.service.PostService;
import ro.bar.sanymotors.session.PostData;
import ro.bar.sanymotors.utils.DataUtils;

@Controller
@SessionAttributes({"propertiesData", "postData"})
public class AdminMainController {
	
	private final String USER_ID = "admin";
	private final String PASSWORD_ID = "Virgil10";
	
	@Autowired
	private PostService postService;
	  
	@Autowired
	private GenericService genericService;
	  
	@RequestMapping({"/administration"})
	public void startAdministration(HttpSession session, HttpServletResponse response) throws IOException{
		response.sendRedirect("administration/login");
	}
	  
	@RequestMapping({"/administration/login"})
	public ModelAndView startLogin(HttpServletResponse response, HttpSession session,
		@ModelAttribute("propertiesData") PropertiesData propertiesData,
		@RequestParam(value="username", required=false) String userName,
		@RequestParam(value="password", required=false) String password) throws IOException {
    	if ((StringUtils.isNotBlank(userName)) && (StringUtils.isNotEmpty(password))) {
    		if ((userName.equals(USER_ID)) && (password.equals(PASSWORD_ID))) {
    			response.sendRedirect("../administration/posts");
    			propertiesData.setLoggedIn(true);
    			session.setAttribute("state", "success");
    			session.setAttribute("isAdmin", Boolean.valueOf(true));
    		} else {
    			session.setAttribute("state", "error");
    		}
    	}
    	return new ModelAndView("adminLogin");
	}
	  	
	@RequestMapping({"/administration/posts"})
	public ModelAndView initiateAdminPanel(HttpSession session,
			@ModelAttribute("propertiesData") PropertiesData propertiesData,
			@RequestParam(value="attrName", required=false) String[] attributeNames,
			@RequestParam(value="attrValue", required=false) String[] attributeValues,
			@RequestParam(value="interfaceValue", required=false) String[] interfaceValues,
			@RequestParam(value="save", required=false) String save,
			@RequestParam(value="stats", required=false) String status,
			@ModelAttribute PostData postData) throws SQLException {
		
		Map<String, Object> modelMap = new HashMap<>();
	    if ((session.getAttribute("isAdmin") != null) && (((Boolean)session.getAttribute("isAdmin")).booleanValue())) {
	    	if (StringUtils.isNotBlank(save)) {
	    		PostImpl foundPost;
	    		if (StringUtils.isNotBlank(postData.getElementId())) {
	    			foundPost = (PostImpl) genericService.getEntity("PostImpl", postData.getElementId());
	    			modelMap.put("msg", "Anuntul a fost modificat cu succes!");
	    		} else {
	    			foundPost = new PostImpl();
	    			modelMap.put("msg", "Anuntul a fost salvat cu succes!");
	    		}
	    		int pos = 0;
	    		List<Attribute> attributes = new ArrayList<>();
	    		if (attributeNames != null)
	    			for (String attributeName : attributeNames) {
	    				String attributeValue = attributeValues[pos];
	    				String interfaceValue = interfaceValues[(pos++)];
	    				AttributeImpl attribute = new AttributeImpl(attributeName, attributeValue, interfaceValue.equals("1"));
	    				attributes.add(attribute);
	    			}
	    		if (StringUtils.isNotBlank(status)) {
	    			postData.setState(Integer.parseInt(status));
	    		} else
	    			postData.setState(0);
	    		CategoryImpl category = (CategoryImpl) genericService.getEntity("CategoryImpl", postData.getCategory());
	    		Image foundMainImage = foundPost.getMainImage();
	    		DataUtils.populatePost(genericService, postData, foundPost);
	    		if (foundPost.getAttributes() != null)
	    			foundPost.getAttributes().clear();
	    		for (Attribute attribute : attributes)
	    			genericService.addEntity(attribute);
	    		foundPost.addAllAttribues(attributes);
	    		if (foundPost.getElementId() != -1) {
	    			genericService.modifyEntity(foundPost);
	    		} else {
	    			category.addPost(foundPost);
	    			genericService.modifyEntity(category);
	    		}
	    		if ((foundMainImage != null) && (((ImageImpl)foundMainImage).getElementId() != -1))
	    			genericService.removeEntity(foundMainImage);
	    	}
	    	propertiesData.setLastPage(postService.getLastPage(Integer.parseInt(propertiesData.getAdminPageItems())));
	    	modelMap.put("page", 1);
	    	DataUtils.emptyPostData(postData);
	    	modelMap.put("announces", postService.getAllAnnounces(1, Integer.parseInt(propertiesData.getAdminPageItems())));
	    	return new ModelAndView("adminMainPanel", modelMap);
	    }
	    modelMap.put("error", "Nu aveti dreptul de a accesa aceasta adresa!");
	    return new ModelAndView("clientError", modelMap);
	}
	
	@RequestMapping({"/administration/updateImage"})
	@ResponseBody
	public String updateImage(@RequestParam(value="x", required=false) String x,
			@RequestParam(value="y", required=false) String y,
			@RequestParam(value="mainWidth", required=false) String mainWidth,
			@RequestParam(value="origWidth", required=false) String origWidth,
			@RequestParam(value="origHeight", required=false) String origHeight,
			@RequestParam(value="width", required=false) String width,
			@RequestParam(value="height", required=false) String height,
			@RequestParam(value="scale", required=false) String scale,
			@RequestParam(value="base64Content", required=true) String base64Content,
			@RequestParam(value="extension", required=true) String extension) throws IOException {
		
		base64Content.replace(" ", "");
	    String newBase64Content = base64Content;
	    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
	    ImageReader reader = (ImageReader)readers.next();
	    byte[] imageBytes = Base64.decodeBase64(newBase64Content);
	    ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
	    Object source = in;
	    ImageInputStream iis = ImageIO.createImageInputStream(source);
	    reader.setInput(iis, true);
	    ImageReadParam param = reader.getDefaultReadParam();
	    BufferedImage image = reader.read(0, param);
	    if ((StringUtils.isNotBlank(origWidth)) && (StringUtils.isNotBlank(origHeight))) {
	    	Double dummy = Double.valueOf(Double.parseDouble(origWidth));
	    	int origWidthValue = dummy.intValue();
	    	dummy = Double.valueOf(Double.parseDouble(origHeight));
	    	int origHeightValue = dummy.intValue();
	    	ResampleOp resampleOp = new ResampleOp(origWidthValue, origHeightValue);
	    	resampleOp.setUnsharpenMask(UnsharpenMask.Normal);
	    	BufferedImage resizedImage = resampleOp.filter(image, null);
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	ImageIO.write(resizedImage, extension, bos);
	    	image = resizedImage;
	    }
	    if ((StringUtils.isNotBlank(scale)) && (StringUtils.isNotBlank(scale))) {
	    	int scaleValue = Integer.parseInt(scale);
	    	ResampleOp resampleOp = new ResampleOp(image.getWidth() + scaleValue, image.getHeight() + scaleValue);
	    	resampleOp.setUnsharpenMask(UnsharpenMask.Normal);
	    	BufferedImage resizedImage = resampleOp.filter(image, null);
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	ImageIO.write(resizedImage, extension, bos);
	    	image = resizedImage;
	    }
	    if ((StringUtils.isNotBlank(x)) && (StringUtils.isNotBlank(y)) && (StringUtils.isNotBlank(width)) && (StringUtils.isNotBlank(height))) {
	    	if (StringUtils.isNotBlank(mainWidth)) {
	    		ResampleOp resampleOp = new ResampleOp(500, 300);
	    		resampleOp.setUnsharpenMask(UnsharpenMask.Normal);
	    		BufferedImage resizedImage = resampleOp.filter(image, null);
	    		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    		ImageIO.write(resizedImage, extension, bos);
	    		image = resizedImage;
	    	}
	    	Double dummy = Double.valueOf(Double.parseDouble(x));
	    	int xValue = dummy.intValue();
	    	dummy = Double.valueOf(Double.parseDouble(y));
	    	int yValue = dummy.intValue();
	    	dummy = Double.valueOf(Double.parseDouble(width));
	    	int widthValue = dummy.intValue();
	    	dummy = Double.valueOf(Double.parseDouble(height));
	    	int heightValue = dummy.intValue();
	    	BufferedImage subImage = image.getSubimage(xValue, yValue, widthValue, heightValue);
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	ImageIO.write(subImage, extension, bos);
	    	image = subImage;
	    }
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ImageIO.write(image, extension, bos);
	    byte[] newImageBytes = bos.toByteArray();
	    newBase64Content = Base64.encodeBase64String(newImageBytes);
	    return newBase64Content;
	}
	  
	@RequestMapping({"/administration/chunks"})
	@ResponseBody
	public String setChunkImage(@RequestParam(value="content", required=true) String content,
			@RequestParam(value="finish", required=false) String finish,
			@RequestParam(value="extension", required=false) String extension,
			@ModelAttribute("postData") PostData postData) throws IOException {
		
		String contentResult = "";
	    if (StringUtils.isBlank(finish)) {
	    	postData.setChunk(postData.getChunk() + content);
	    } else {
	      postData.setChunk(postData.getChunk() + content);
	      String base64Value = postData.getChunk();
	      base64Value = base64Value.replace(" ", "+");
	      postData.setChunk("");
	      Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
	      ImageReader reader = (ImageReader)readers.next();
	      byte[] imageBytes = Base64.decodeBase64(base64Value);
	      ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
	      Object source = in;
	      ImageInputStream iis = ImageIO.createImageInputStream(source);
	      reader.setInput(iis, true);
	      ImageReadParam param = reader.getDefaultReadParam();
	      BufferedImage image = reader.read(0, param);
	      int width = 2048;
	      int height = Math.round((float)(2048 * image.getHeight() / image.getWidth()));
	      ResampleOp resampleOp = new ResampleOp(width, height);
	      resampleOp.setUnsharpenMask(UnsharpenMask.Normal);
	      BufferedImage resizedImage = resampleOp.filter(image, null);
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(resizedImage, extension, bos);
	      byte[] newImageBytes = bos.toByteArray();
	      contentResult = Base64.encodeBase64String(newImageBytes);
	    }
	    return contentResult;
	}
}