package ro.bar.sanymotors.data;

public class Announce {
	
	private int index;
	private String image;
	private String title;
	private String description;
	private String dateCreated;
	private String categoryName;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Announce (int index, String title, String description, String image, String dateCreated, String categoryName){
		this.index = index;
		this.title = title;
		this.image = image;
		this.description = description;
	    this.image = image;
	    this.dateCreated = dateCreated;
	    this.categoryName = categoryName;
	}
}
