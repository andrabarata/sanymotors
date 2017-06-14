package ro.bar.sanymotors.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

import ro.bar.sanymotors.data.PropertiesData;
import ro.bar.sanymotors.service.PostService;
import ro.bar.sanymotors.session.PostData;

@Controller
public class AdminMainController {
	private final String userID = "admin";
	private final String passwordID = "password";
	
	@Autowired
	private PropertiesData propertiesData;
	
	@Autowired
	private PostService postService;

	@RequestMapping(value="/administration")
	public void startAdministration(HttpSession session,
			HttpServletResponse response) throws IOException{
		session.setAttribute("propertiesData", propertiesData);
		response.sendRedirect("login");
	}
	
	@RequestMapping(value="/administration/login")
	public ModelAndView startLogin(
			HttpServletResponse response,
			HttpSession session,
			@RequestParam(value = "username", required = false) String userName,
			@RequestParam(value = "password", required = false) String password) throws IOException{
		
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotEmpty(password)){
			if (userName.equals(userID) && password.equals(passwordID)){
				response.sendRedirect("../administration/posts");
				PropertiesData data= (PropertiesData) session.getAttribute("propertiesData");
				data.setLoggedIn(true);
				session.setAttribute("propertiesData", data);
				session.setAttribute("state", "success");
			} else {
				session.setAttribute("state", "error");
			}
		} 
		return new ModelAndView("adminLogin");
	}
	
	@RequestMapping(value="/administration/posts")
	public ModelAndView initiateAdminPanel(HttpSession session) throws SQLException{
		Map<String, Object> modelMap = new HashMap<>();
		PropertiesData data = (PropertiesData) session.getAttribute("propertiesData");
		data.setLastPage(postService.getLastPage(Integer.parseInt(data.getPageItems())));
		session.setAttribute("propertiesData", data);
		session.setAttribute("postData", new PostData());
		modelMap.put("page", 1);
		modelMap.put("announces", postService.getAllAnnounces(1,Integer.parseInt(data.getPageItems())));
		return new ModelAndView("adminMainPanel",modelMap);
	}
	@RequestMapping(value="/administration/updateImage")
	@ResponseBody
	public String updateImage(
			@RequestParam(value = "x", required = false) String x,
			@RequestParam(value = "y", required = false) String y,
			@RequestParam(value = "width", required = false) String width,
			@RequestParam(value = "height", required = false) String height,
			@RequestParam(value = "scale", required = false) String scale,
			@RequestParam(value = "base64Content", required = true) String base64Content,
			@RequestParam(value = "extension", required = true) String extension) throws IOException{
		base64Content.replace(" ", "");
		String newBase64Content = base64Content;
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(extension);
		ImageReader reader = (ImageReader) readers.next();
		if (StringUtils.isNotBlank(scale) && StringUtils.isNotBlank(scale)){
			byte[] imageBytes = Base64.decodeBase64(newBase64Content);
			ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
			Object source = in; 
			ImageInputStream iis = ImageIO.createImageInputStream(source); 
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			BufferedImage image = reader.read(0, param);
			int scaleValue = Integer.parseInt(scale);
			ResampleOp  resampleOp = new ResampleOp (image.getWidth()+scaleValue,image.getHeight()+scaleValue);
			resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
			BufferedImage resizedImage = resampleOp.filter(image, null);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(resizedImage, extension, bos);
			byte[] newImageBytes = bos.toByteArray();
			newBase64Content = Base64.encodeBase64String(newImageBytes);
		}
		if (StringUtils.isNotBlank(x) && StringUtils.isNotBlank(y) && StringUtils.isNotBlank(width) && StringUtils.isNotBlank(height)){
			byte[] imageBytes = Base64.decodeBase64(newBase64Content);
			ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
			Object source = in; 
			ImageInputStream iis = ImageIO.createImageInputStream(source); 
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			BufferedImage image = reader.read(0, param);
			Double dummy = Double.parseDouble(x);
			int xValue = dummy.intValue();
			dummy = Double.parseDouble(y);
			int yValue = dummy.intValue();
			dummy = Double.parseDouble(width);
			int widthValue = dummy.intValue();
			dummy = Double.parseDouble(height);
			int heightValue = dummy.intValue();
			BufferedImage subImage = image.getSubimage(xValue, yValue, widthValue, heightValue);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(subImage, extension, bos);
			byte[] newImageBytes = bos.toByteArray();
			newBase64Content = Base64.encodeBase64String(newImageBytes);
		}
		return newBase64Content;
	}
}
