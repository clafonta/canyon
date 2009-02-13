package org.tll.canyon.model.viewwrappers;

import org.tll.canyon.model.AssetDetail;

public class AssetDetailInfo {
	public AssetDetailInfo(AssetDetail assetDetail){
		this.id = assetDetail.getId();
		this.assetName = assetDetail.getAssetName();
		this.assetDescription = assetDetail.getAssetDescription();
		this.assetLocation = assetDetail.getAssetLocation();
		this.assetAddress = assetDetail.getAssetAddress();
	}
	public Long getId() {
		return id;
	}
	public String getAssetName() {
		return assetName;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public String getAssetUsageType() {
		return assetUsageType;
	}
	public String getAssetLocation() {
		return assetLocation;
	}
	public String getAssetAddress() {
		return assetAddress;
	}
	
	private Long id;
    private String assetName;
    private String assetDescription;
    private String assetUsageType;
    private String assetLocation;
    private String assetAddress;
    
}
