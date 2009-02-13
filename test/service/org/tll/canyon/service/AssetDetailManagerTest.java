package org.tll.canyon.service;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetDetailDao;
import org.tll.canyon.model.AssetDetail;
import org.tll.canyon.service.AssetDetailManager;
import org.tll.canyon.service.impl.AssetDetailManagerImpl;


public class AssetDetailManagerTest extends BaseManagerTestCase {
    private final String assetDetailId = "1";
    private AssetDetailManager assetDetailManager = new AssetDetailManagerImpl();
    private Mock assetDetailDao = null;
    private AssetDetail assetDetail = null;

    protected void setUp() throws Exception {
        super.setUp();
        assetDetailDao = new Mock(AssetDetailDao.class);
        assetDetailManager.setAssetDetailDao((AssetDetailDao) assetDetailDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        assetDetailManager = null;
    }
    
    public void testGetAssetDetail() throws Exception {
        // set expected behavior on dao
        assetDetailDao.expects(once()).method("getAssetDetail")
            .will(returnValue(new AssetDetail()));
        assetDetail = assetDetailManager.getAssetDetail(assetDetailId);
        assertTrue(assetDetail != null);
        assetDetailDao.verify();
    }

    public void testSaveAssetDetail() throws Exception {
        // set expected behavior on dao
        assetDetailDao.expects(once()).method("saveAssetDetail")
            .with(same(assetDetail)).isVoid();

        assetDetailManager.saveAssetDetail(assetDetail);
        assetDetailDao.verify();
    }    

    public void testAddAndRemoveAssetDetail() throws Exception {
        assetDetail = new AssetDetail();

        // set required fields
        assetDetail.setAssetName("CMD");
        assetDetail.setEnabled(true);

        // set expected behavior on dao
        assetDetailDao.expects(once()).method("saveAssetDetail")
            .with(same(assetDetail)).isVoid();
        assetDetailManager.saveAssetDetail(assetDetail);
        assetDetailDao.verify();

        // reset expectations
        assetDetailDao.reset();

        assetDetailDao.expects(once()).method("removeAssetDetail").with(eq(new Long(assetDetailId)));
        assetDetailManager.removeAssetDetail(assetDetailId);
        assetDetailDao.verify();

        // reset expectations
        assetDetailDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(AssetDetail.class, assetDetail.getId());
        assetDetailDao.expects(once()).method("removeAssetDetail").isVoid();            
        assetDetailDao.expects(once()).method("getAssetDetail").will(throwException(ex));
        assetDetailManager.removeAssetDetail(assetDetailId);
        try {
            assetDetailManager.getAssetDetail(assetDetailId);
            fail("AssetDetail with identifier '" + assetDetailId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        assetDetailDao.verify();
    }
    
    @SuppressWarnings("unchecked")
    public void testGetAssetDetails() throws Exception {
        List results = new ArrayList();
        assetDetail = new AssetDetail();
        results.add(assetDetail);

        // set expected behavior on dao
        assetDetailDao.expects(once()).method("getAssetDetails")
            .will(returnValue(results));

        List people = assetDetailManager.getAssetDetails(null);
        assertTrue(people.size() == 1);
        assetDetailDao.verify();
    }
}
