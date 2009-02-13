package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.service.AssetDetailManager;


public class AssetDetailManagerImpl extends BaseManager implements AssetDetailManager {
    private AssetDetailDao dao;

    public void setAssetDetailDao(AssetDetailDao dao) {
        this.dao = dao;
    }

    public AssetDetail getAssetDetail(String id) {
        return dao.getAssetDetail(Long.valueOf(id));
    }

    public void saveAssetDetail(AssetDetail assetDetail) {
        dao.saveAssetDetail(assetDetail);
    }

    public void removeAssetDetail(String id) {
        dao.removeAssetDetail(Long.valueOf(id));
    }
    
    public List<AssetDetail> getAssetDetails(AssetDetail assetDetail) {
        return dao.getAssetDetails(assetDetail);
    }
    
    public AssetDetail getAssetDetailByName(String name){
        return dao.getAssetDetailByName(name);
    }
}
