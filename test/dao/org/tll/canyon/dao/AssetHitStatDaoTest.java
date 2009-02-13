package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.dao.AssetHitStatDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.model.AssetHitStat;


public class AssetHitStatDaoTest extends BaseDaoTestCase {
    
    private AssetHitStat assetHitStat = null;
    private AssetHitStatDao assetHitStatDao = null;
    private AssetDetail assetDetail = null;
    private AssetDetailDao assetDetailDao = null;
    private List<AssetHitStat> assetHitStatList = null;
    
    public void setAssetHitStatDao(AssetHitStatDao dao) {
        this.assetHitStatDao = dao;
    }
    public void setAssetDetailDao(AssetDetailDao dao) {
        this.assetDetailDao = dao;
    }
        
    public void testGetAssetHitStat() throws Exception {
        assetDetail = new AssetDetail();
        assetDetail.setAssetName("CMD");
        assetDetailDao.saveAssetDetail(assetDetail);
        assertNotNull(assetDetail.getId());        
        
        assetHitStat = new AssetHitStat();
        assetHitStat.setAssetDetailId(assetDetail.getId());
        assetHitStat.setApplicationUsedToConnect("MSC.exe");        
        assetHitStatDao.saveAssetHitStat(assetHitStat);
        assertNotNull(assetHitStat.getId());
        assertNotNull(assetHitStat.getAssetDetailId());

        assetHitStat = assetHitStatDao.getAssetHitStat(assetHitStat.getId());
        assertEquals(assetHitStat.getApplicationUsedToConnect(), "MSC.exe");
    }
    
    public void testGetAssetHitStatList() throws Exception {
       
        
        assetDetail = assetDetailDao.getAssetDetail(new Long(1));
        assetHitStatList = assetHitStatDao.getAssetHitStatsForAssetDetail(assetDetail);
        assertEquals(4,assetHitStatList.size());
        
    }
    
    public void testSaveAssetHitStat() throws Exception {
        assetHitStat = new AssetHitStat();
        assetHitStat.setApplicationUsedToConnect("MSC.exe");
        assetHitStatDao.saveAssetHitStat(assetHitStat);
        assertEquals(assetHitStat.getApplicationUsedToConnect(), "MSC.exe");
        
    }
    
    
    
    

}
