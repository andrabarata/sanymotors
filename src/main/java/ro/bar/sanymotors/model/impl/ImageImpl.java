package ro.bar.sanymotors.model.impl;

import ro.bar.sanymotors.model.Image;
import ro.bar.sanymotors.model.SerializableElement;

public class ImageImpl extends SerializableElement implements Image {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String base64Content;
	
	public String getBase64Content() {
		return base64Content;
	}
	public void setBase64Content(String base64Content) {
		this.base64Content = base64Content;
	}

}
