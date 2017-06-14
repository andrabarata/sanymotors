package ro.bar.sanymotors.model.impl;

import ro.bar.sanymotors.model.Attribute;
import ro.bar.sanymotors.model.SerializableElement;

public class AttributeImpl extends SerializableElement implements Attribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private Boolean interfaceValue;
	
	public AttributeImpl(){
		
	}
	
	public AttributeImpl(String name, String value, Boolean interfaceValue) {
		this.name = name;
		this.value = value;
		this.interfaceValue = interfaceValue;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getInterfaceValue() {
		return interfaceValue;
	}
	public void setInterfaceValue(Boolean interfaceValue) {
		this.interfaceValue = interfaceValue;
	}
	
}
