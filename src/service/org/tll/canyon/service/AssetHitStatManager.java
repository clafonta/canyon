package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.dao.AssetHitStatDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;


public interface AssetHitStatManager {
    public void setAssetHitStatDao(AssetHitStatDao dao);
    public AssetHitStat getAssetHitStat(String assetHitStatId);
    public void saveAssetHitStat(AssetHitStat assetHitStat);
    public void removeAssetHitStat(String assetHitStatId);
    public List<AssetHitStat> getAssetHitStatsByAssetDetail(AssetDetail assetDetail);
    public List<AssetHitStat> getAssetHitStats(AssetHitStat assetHitStat);
    public List<AssetHitStat> getAssetHitStatsBySearch(AssetHitStatSearchForm assetHitStatSearchForm);
}

