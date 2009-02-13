package org.tll.canyon.model;

public class AssetType {

	private String typeName;
	private String typeDescription;
	private AssetType(){};
	private AssetType(String typeName, String typeDescription){
		this.typeName = typeName;
		this.typeDescription = typeDescription;
	};
	
	public static final AssetType OTHER = new AssetType("other", "Other");
	public static final AssetType APPSERVER = new AssetType("appserver", "Application Server");
	public static final AssetType APPLICATION = new AssetType("application", "Application");
	public static final AssetType WEBSERVER = new AssetType("webserver", "Web Server");
	public static final AssetType DBSERVER = new AssetType("database", "Database");
	public static final AssetType[] ASSETTYPES = new AssetType[]{AssetType.OTHER, AssetType.APPLICATION, AssetType.APPSERVER, AssetType.DBSERVER, AssetType.WEBSERVER};
	public String getTypeName() {
		return typeName;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	
}
