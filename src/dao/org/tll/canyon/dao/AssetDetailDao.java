package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetDetail;


public interface AssetDetailDao extends Dao {
    public AssetDetail getAssetDetail(Long assetDetailId);
    public void saveAssetDetail(AssetDetail assetDetail);
    public void removeAssetDetail(Long assetDetailId);
    public List<AssetDetail> getAssetDetails(AssetDetail assetDetail);
    public AssetDetail getAssetDetailByName(String name);
}


