package org.tll.canyon.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.model.viewwrappers.AssetRoleInfo;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.AssetRoleInfoManager;


public class AssetRoleInfoManagerImpl implements AssetRoleInfoManager {

    private AssetDetailManager assetDetailManager;

    /**
     * 
     * @param assetDetailManager
     */
    public void setAssetDetailManager(AssetDetailManager assetDetailManager) {
        this.assetDetailManager = assetDetailManager;
    }

    /**
     * Gets list of AssetRoleInfo information based on id.
     * @param id the assetRole's id
     * @return assetRole populated assetRole object
     */    
    @SuppressWarnings("unchecked")
    public List<AssetRoleInfo> getAssetRoleInfo(String assetDetailId) {
        AssetDetail assetDetail = this.assetDetailManager.getAssetDetail(assetDetailId);
        List<AssetRole> roles = assetDetail.getAssetRoles();
        List<AssetRoleInfo> toReturn = new ArrayList<AssetRoleInfo>();
        for (Iterator iter = roles.iterator(); iter.hasNext();) {
            AssetRole assetRole = (AssetRole) iter.next();
            AssetRoleInfo assetRoleInfo = new AssetRoleInfo(assetRole);
            toReturn.add(assetRoleInfo);
        }
        return toReturn;
    }

}
