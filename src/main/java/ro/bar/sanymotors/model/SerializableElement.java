package ro.bar.sanymotors.model;

import java.io.Serializable;

public class SerializableElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int elementId;
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	public int getElementId(){
		return elementId;
	}
	
}
