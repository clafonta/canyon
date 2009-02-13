package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetHitStatDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;
import org.tll.canyon.service.AssetHitStatManager;


public class AssetHitStatManagerImpl extends BaseManager implements AssetHitStatManager  {
    private AssetHitStatDao dao = null;
    public void setAssetHitStatDao(AssetHitStatDao dao){
        this.dao = dao;
    }
    public AssetHitStat getAssetHitStat(String assetHitStatId){
        return this.dao.getAssetHitStat(Long.valueOf(assetHitStatId));
    }
    public void saveAssetHitStat(AssetHitStat assetHitStat){
        this.dao.saveAssetHitStat(assetHitStat);
    }
    public void removeAssetHitStat(String assetHitStatId){
        this.dao.removeAssetHitStat(Long.valueOf(assetHitStatId));        
    }
    public List<AssetHitStat> getAssetHitStatsByAssetDetail(AssetDetail assetDetail){
        return this.dao.getAssetHitStatsForAssetDetail(assetDetail);
    }
    public List<AssetHitStat> getAssetHitStats(AssetHitStat assetHitStat){
        return this.dao.getAssetHitStats(assetHitStat);
    }
    public List<AssetHitStat> getAssetHitStatsBySearch(AssetHitStatSearchForm assetHitStatSearchForm){
        return this.dao.getAssetHitStatsBySearch(assetHitStatSearchForm);
    }
}
