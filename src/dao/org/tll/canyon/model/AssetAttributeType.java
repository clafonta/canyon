package org.tll.canyon.model;

public class AssetAttributeType {

	private String type;
	private boolean multiValue;
	
	private AssetAttributeType(){
		
	}
	
	private AssetAttributeType(String type, boolean multipleValueSupport){
		this.type = type;
		this.multiValue = multipleValueSupport;
	}
	
	public String toString(){
		return type;
	}

	public boolean isMultiValue() {
		return multiValue;
	}

	//private final String[] supportedTypes = new String[]{"string", "float", "date", "boolean", "radio", "dropdown", "checkboxes"};
	public static final AssetAttributeType STRING = new AssetAttributeType("STRING", false);
	public static final AssetAttributeType FLOAT = new AssetAttributeType("FLOAT", false);
	public static final AssetAttributeType DATE = new AssetAttributeType("DATE", false);
	public static final AssetAttributeType RADIO = new AssetAttributeType("RADIO", true);
	public static final AssetAttributeType DROPDOWN = new AssetAttributeType("DROPDOWN", true);
	public static final AssetAttributeType CHECKBOX = new AssetAttributeType("CHECKBOX", true);
}
