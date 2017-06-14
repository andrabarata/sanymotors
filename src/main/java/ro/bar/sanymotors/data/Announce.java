package ro.bar.sanymotors.data;

public class Announce {
	
	private int index;
	private String image;
	private String title;
	
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
	
	public Announce (int index, String title, String image){
		this.index = index;
		this.title = title;
		this.image = image;
	}
}
