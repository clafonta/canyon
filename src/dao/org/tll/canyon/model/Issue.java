package org.tll.canyon.model;

public class Issue {

	public static final String INACTIVE_USER = "INACTIVE_USER";
	private String description;
	private String type;
	
	public Issue(){}
	
	public Issue(String description, String type){
		this.description = description;
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
