package org.tll.canyon.service;

import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetHitStatDao;
import org.tll.canyon.model.AssetHitStat;
import org.tll.canyon.service.AssetHitStatManager;
import org.tll.canyon.service.impl.AssetHitStatManagerImpl;


public class AssetHitStatManagerTest extends BaseManagerTestCase {
    private final String assetHitStatId = "1";
    private AssetHitStatManager assetHitStatManager = new AssetHitStatManagerImpl();
    private Mock assetHitStatDao = null;
    private AssetHitStat assetHitStat = null;

    protected void setUp() throws Exception {
        super.setUp();
        assetHitStatDao = new Mock(AssetHitStatDao.class);
        assetHitStatManager.setAssetHitStatDao((AssetHitStatDao) assetHitStatDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        assetHitStatManager = null;
    }
    
    public void testGetAssetHitStat() throws Exception {
        // set expected behavior on dao
        assetHitStatDao.expects(once()).method("getAssetHitStat")
            .will(returnValue(new AssetHitStat()));
        assetHitStat = assetHitStatManager.getAssetHitStat(assetHitStatId);
        assertTrue(assetHitStat != null);
        assetHitStatDao.verify();
    }

    public void testSaveAssetHitStat() throws Exception {
        // set expected behavior on dao
        assetHitStatDao.expects(once()).method("saveAssetHitStat")
            .with(same(assetHitStat)).isVoid();

        assetHitStatManager.saveAssetHitStat(assetHitStat);
        assetHitStatDao.verify();
    }    

    public void testAddAndRemoveAssetHitStat() throws Exception {
        assetHitStat = new AssetHitStat();

        // set required fields
        assetHitStat.setApplicationUsedToConnect("MSC.exe");

        // set expected behavior on dao
        assetHitStatDao.expects(once()).method("saveAssetHitStat")
            .with(same(assetHitStat)).isVoid();
        assetHitStatManager.saveAssetHitStat(assetHitStat);
        assetHitStatDao.verify();

        // reset expectations
        assetHitStatDao.reset();

        assetHitStatDao.expects(once()).method("removeAssetHitStat").with(eq(new Long(assetHitStatId)));
        assetHitStatManager.removeAssetHitStat(assetHitStatId);
        assetHitStatDao.verify();

        // reset expectations
        assetHitStatDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(AssetHitStat.class, assetHitStat.getId());
        assetHitStatDao.expects(once()).method("removeAssetHitStat").isVoid();            
        assetHitStatDao.expects(once()).method("getAssetHitStat").will(throwException(ex));
        assetHitStatManager.removeAssetHitStat(assetHitStatId);
        try {
            assetHitStatManager.getAssetHitStat(assetHitStatId);
            fail("AssetHitStat with identifier '" + assetHitStatId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        assetHitStatDao.verify();
    }
}
