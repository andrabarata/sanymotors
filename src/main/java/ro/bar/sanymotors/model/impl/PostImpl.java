package ro.bar.sanymotors.model.impl;

import java.util.ArrayList;
import java.util.List;

import ro.bar.sanymotors.model.Image;
import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.model.SerializableElement;

public class PostImpl extends SerializableElement implements Post{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private int state;
	
	private Image mainImage;
	private List<Image>additionalImages;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Image getMainImage() {
		return mainImage;
	}
	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}
	public List<Image> getAdditionalImages() {
		return additionalImages;
	}
	public void setAdditionalImages(List<Image> additionalImages) {
		this.additionalImages = additionalImages;
	}
	
	public void addAditionalImage(Image image){
		if (additionalImages == null)
			additionalImages = new ArrayList<>();
		additionalImages.add(image);
	}
}
