package org.tll.canyon.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.model.AssetDetail;


public class AssetDetailDaoTest extends BaseDaoTestCase {
    
    private AssetDetail assetDetail = null;
    private AssetDetailDao dao = null;

    public void setAssetDetailDao(AssetDetailDao dao) {
        this.dao = dao;
    }
    
    public void testGetAssetDetail() throws Exception {
        assetDetail = new AssetDetail();
        assetDetail.setAssetName("CMD");        
        assetDetail.setEnabled(true);

        dao.saveAssetDetail(assetDetail);
        assertNotNull(assetDetail.getId());

        assetDetail = dao.getAssetDetail(assetDetail.getId());
        assertEquals(assetDetail.getAssetName(), "CMD");
    }

    public void testSaveAssetDetail() throws Exception {
        assetDetail = dao.getAssetDetail(new Long(1));
        assetDetail.setAssetName("CMD");
        assetDetail.setEnabled(true);
       

        dao.saveAssetDetail(assetDetail);

        if (log.isDebugEnabled()) {
            log.debug("updated AssetDetail: " + assetDetail);
        }

        assertEquals(assetDetail.getAssetName(), "CMD");
    }

    public void testAddAndRemoveAssetDetail() throws Exception {
        assetDetail = new AssetDetail();
        assetDetail.setAssetName("CMD");     
        assetDetail.setEnabled(true);
        dao.saveAssetDetail(assetDetail);

        assertEquals(assetDetail.getAssetName(), "CMD");
        assertNotNull(assetDetail.getId());

        if (log.isDebugEnabled()) {
            log.debug("removing assetDetail...");
        }

        dao.removeAssetDetail(assetDetail.getId());

        try {
            assetDetail = dao.getAssetDetail(assetDetail.getId());
            fail("AssetDetail found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }
    
    public void testGetAssetDetails() {
        assetDetail = new AssetDetail();
        List<AssetDetail> results = dao.getAssetDetails(assetDetail);
        assertTrue(results.size() > 0);
    }
}