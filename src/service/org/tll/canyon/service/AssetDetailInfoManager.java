package org.tll.canyon.service;

import org.tll.canyon.model.viewwrappers.AssetDetailInfo;

/**
 * For DWR/AJAX usage, wrapper to provide read only access to 
 * AssetDetail information. 
 * 
 * @author Chad.Lafontaine
 *
 */
public interface AssetDetailInfoManager {
	
    public void setAssetDetailManager(AssetDetailManager assetDetailManager);
	public AssetDetailInfo getAssetDetailInfo(String id);
}
