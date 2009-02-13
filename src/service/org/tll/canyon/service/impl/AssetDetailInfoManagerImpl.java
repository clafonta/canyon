/**
 * 
 */
package org.tll.canyon.service.impl;

import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.viewwrappers.AssetDetailInfo;
import org.tll.canyon.service.AssetDetailInfoManager;
import org.tll.canyon.service.AssetDetailManager;

/**
 * @author Chad.Lafontaine
 *
 */
public class AssetDetailInfoManagerImpl implements AssetDetailInfoManager {

	private AssetDetailManager assetDetailManager;
	
	/* (non-Javadoc)
	 * @see org.tll.canyon.service.AssetDetailInfoManager#getAssetDetailInfo(java.lang.String)
	 */
	public AssetDetailInfo getAssetDetailInfo(String id) {
		
		AssetDetail ad = this.assetDetailManager.getAssetDetail(id);
		AssetDetailInfo adi = null;
		if(ad!=null){
			adi = new AssetDetailInfo(ad);
		}
		return adi;
	}

	/* (non-Javadoc)
	 * @see org.tll.canyon.service.AssetDetailInfoManager#setAssetDetailManager(org.tll.canyon.service.AssetDetailManager)
	 */
	public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
		this.assetDetailManager = assetDetailManager;
	}

}
