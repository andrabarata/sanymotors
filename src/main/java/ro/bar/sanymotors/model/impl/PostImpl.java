package ro.bar.sanymotors.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.bar.sanymotors.model.Attribute;
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
	private Date dateCreated;
	private int state;
	private double price;
	
	private Image mainImage;
	private List<Image>additionalImages;
	private List<Attribute> attributes;
	
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
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(Attribute attribute) {
	    if (attributes == null)
	    	attributes = new ArrayList<>();
	    attributes.add(attribute);
	}
	
	public void setNewCreationDate(){
		dateCreated = new Date();
	}
	
	public void addAllAttribues(List<Attribute> attributes) {
	    if (this.attributes != null) {
	    	this.attributes.clear();
	    } else if (this.attributes == null) {
	    	this.attributes = new ArrayList<>();
	    }
	    this.attributes.addAll(attributes);
	}
}
