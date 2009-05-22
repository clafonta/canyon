package org.tll.canyon.model.viewwrappers;

import org.tll.canyon.model.AssetDetail;

public class AssetDetailInfo {
	public AssetDetailInfo(AssetDetail assetDetail){
		this.id = assetDetail.getId();
		this.assetName = assetDetail.getAssetName();
	
	}
	public Long getId() {
		return id;
	}
	public String getAssetName() {
		return assetName;
	}
	
	
	private Long id;
    private String assetName;
    
}
