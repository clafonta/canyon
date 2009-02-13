
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.viewwrappers.AssetRoleInfo;


public interface AssetRoleInfoManager {
   
    /**
     * 
     * @param assetDetailManager
     */
    public void setAssetDetailManager(AssetDetailManager assetDetailManager);
    /**
     * Gets list of AssetRoleInfo information based on id.
     * @param id the assetRole's id
     * @return assetRole populated assetRole object
     */
    public List<AssetRoleInfo> getAssetRoleInfo(String assetDetailId);
    
    
}

