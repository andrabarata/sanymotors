package ro.bar.sanymotors.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ro.bar.sanymotors.model.Post;
import ro.bar.sanymotors.model.impl.PostImpl;

public class PostData {

	private String elementId;
	private String title;
	private String description;
	private String image;
	private String price;
	private String currency;
	private String category;
	private List<String> additionalImages;
	private List<String> availableAttributes;
	private Map<Integer, String> categories;
	private int state;
	private List<String> statusTexts;
	private String chunk = "";
	private List<Post> posts;
	  
	public Map<Integer, String> getCategories() {
		return categories;
	}
	  
	public void setCategories(Map<Integer, String> categories) {
		this.categories = categories;
	}
	  
	public PostData(String elementId, String title, String description, String image) {
		this.elementId = elementId;
	    this.title = title;
	    this.description = description;
	    this.image = image;
	    statusTexts = new ArrayList<String>();
	    statusTexts.add("Motocicleta nu mai e de vanzare");
	    statusTexts.add("Motocicleta se poate inchiria");
	    statusTexts.add("Piesa de vanzare");
	  }
	  
	public String getElementId() {
		return elementId;
	}	
	  
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	  
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
	  
	public String getImage() {
		return image;
	}
	  
	public void setImage(String image) {
		this.image = image;
	}
	  
	public List<String> getAdditionalImages() {
		return additionalImages;
	}
	  
	public void setAdditionalImages(List<String> additionalImages) {
		this.additionalImages = additionalImages;
	}
	  
	public List<String> getAvailableAttributes() {
		return availableAttributes;
	}
	  
	public void setAvailableAttributes(List<String> availableAttributes) {
		this.availableAttributes = availableAttributes;
	}
	  
	public PostData() {
	    statusTexts = new ArrayList<String>();
	    statusTexts.add("Articolul nu mai e de vanzare");
	    statusTexts.add("Articolul se poate inchiria");
	    statusTexts.add("Piesa de vanzare");
	}
	  
	public void addAdditionalImage(String additionalImage) {
		if (additionalImages == null)
			additionalImages = new ArrayList<>();
	    additionalImages.add(additionalImage);
	}
	  
	public void initializeAdditionalImagesList() {
		additionalImages = new ArrayList<>();
	}
	  
	public void addAvailableName(String name) {
		if (availableAttributes == null)
			availableAttributes = new ArrayList<String>();
	    availableAttributes.add(name);
	}
	  
	public String getPrice() {
		return price;
	}
	  
	public void setPrice(String price) {
		this.price = price;
	}
	  
	public String getCurrency() {
		return currency;
	}
	  
	public void setCurrency(String currency) {
	    this.currency = currency;
	}
	  
	public String getCategory() {
		return category;
	}
	  
	public void setCategory(String category) {
	    this.category = category;
	}
	  
	public int getState() {
		return state;
	}
	  
	public void setState(int state) {
	    this.state = state;
	}
	  
	public List<String> getStatusTexts() {
		return statusTexts;
	}
	  
	public void setStatusTexts(List<String> statusTexts) {
	    this.statusTexts = statusTexts;
	}
	  
	public List<Post> getPosts() {
		return posts;
	}
	  
	public void setPosts(List<Post> posts) {
	    this.posts = posts;
	}
	  
	public String getChunk() {
		return chunk;
	}
	  
	public void setChunk(String chunk) {
		this.chunk = chunk;
	}
	  
	public Post getPostById(String elementId) {
	    for (Post post : posts)
	    	if (((PostImpl)post).getElementId() == Integer.parseInt(elementId))
	    		return post;
	    return null;
	}
}