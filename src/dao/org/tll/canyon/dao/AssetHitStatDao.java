package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.model.viewwrappers.AssetHitStatSearchForm;


public interface AssetHitStatDao extends Dao {
    public AssetHitStat getAssetHitStat(Long assetHitStatId);
    public void saveAssetHitStat(AssetHitStat assetHitStat);
    public void removeAssetHitStat(Long assetHitStatId);
    public List<AssetHitStat> getAssetHitStats(AssetHitStat assetHitStat);
    public List<AssetHitStat> getAssetHitStatsForAssetDetail(AssetDetail assetDetail);
    public List<AssetHitStat> getAssetHitStatsBySearch(AssetHitStatSearchForm assetHitStatSearchForm);

}
