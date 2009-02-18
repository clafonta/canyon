package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.model.AssetDetail;


public interface AssetDetailManager  {
    public void setAssetDetailDao(AssetDetailDao dao);
    public AssetDetail getAssetDetail(String id);
    public void saveAssetDetail(AssetDetail assetDetail);
    public void removeAssetDetail(String id);
    public List<AssetDetail> getAssetDetails(AssetDetail assetDetail);
    /**
     * 
     * @param name
     * @return AssetDetail with matching asset detail name, otherwise, returns null. 
     */
    public AssetDetail getAssetDetailByName(String name);
    
}
