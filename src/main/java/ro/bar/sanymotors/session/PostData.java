package ro.bar.sanymotors.session;

import java.util.List;

public class PostData {
	
	private String elementId;
	private String title;
	private String description;
	private String fakeImageName;
	private String image;
	private List<String> additionalImages;
	
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
	public String getFakeImageName() {
		return fakeImageName;
	}
	public void setFakeImageName(String fakeImageName) {
		this.fakeImageName = fakeImageName;
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
	
}
