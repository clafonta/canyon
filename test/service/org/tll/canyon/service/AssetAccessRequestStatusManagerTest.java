
package org.tll.canyon.service;

import java.util.List;
import java.util.ArrayList;


import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetAccessRequestStatusDao;
import org.tll.canyon.model.AssetAccessRequestStatus;
import org.tll.canyon.service.BaseManagerTestCase;
import org.tll.canyon.service.impl.AssetAccessRequestStatusManagerImpl;

public class AssetAccessRequestStatusManagerTest extends BaseManagerTestCase {
    private final String assetAccessRequestStatusId = "1";
    private AssetAccessRequestStatusManagerImpl assetAccessRequestStatusManager = new AssetAccessRequestStatusManagerImpl();
    private Mock assetAccessRequestStatusDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        assetAccessRequestStatusDao = new Mock(AssetAccessRequestStatusDao.class);
        assetAccessRequestStatusManager.setAssetAccessRequestStatusDao((AssetAccessRequestStatusDao) assetAccessRequestStatusDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        assetAccessRequestStatusManager = null;
    }

    public void testGetAssetAccessRequestStatuss() throws Exception {
        List results = new ArrayList();
        AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();
        results.add(assetAccessRequestStatus);

        // set expected behavior on dao
        assetAccessRequestStatusDao.expects(once()).method("getAssetAccessRequestStatuss")
            .will(returnValue(results));

        List assetAccessRequestStatuss = assetAccessRequestStatusManager.getAssetAccessRequestStatusList(null);
        assertTrue(assetAccessRequestStatuss.size() == 1);
        assetAccessRequestStatusDao.verify();
    }

    public void testGetAssetAccessRequestStatus() throws Exception {
        // set expected behavior on dao
        assetAccessRequestStatusDao.expects(once()).method("getAssetAccessRequestStatus")
            .will(returnValue(new AssetAccessRequestStatus()));
        AssetAccessRequestStatus assetAccessRequestStatus = assetAccessRequestStatusManager.getAssetAccessRequestStatus(assetAccessRequestStatusId);
        assertTrue(assetAccessRequestStatus != null);
        assetAccessRequestStatusDao.verify();
    }

    public void testSaveAssetAccessRequestStatus() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();

        // set expected behavior on dao
        assetAccessRequestStatusDao.expects(once()).method("saveAssetAccessRequestStatus")
            .with(same(assetAccessRequestStatus)).isVoid();

        assetAccessRequestStatusManager.saveAssetAccessRequestStatus(assetAccessRequestStatus);
        assetAccessRequestStatusDao.verify();
    }

    public void testAddAndRemoveAssetAccessRequestStatus() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();

        // set required fields

        // set expected behavior on dao
        assetAccessRequestStatusDao.expects(once()).method("saveAssetAccessRequestStatus")
            .with(same(assetAccessRequestStatus)).isVoid();
        assetAccessRequestStatusManager.saveAssetAccessRequestStatus(assetAccessRequestStatus);
        assetAccessRequestStatusDao.verify();

        // reset expectations
        assetAccessRequestStatusDao.reset();

        assetAccessRequestStatusDao.expects(once()).method("removeAssetAccessRequestStatus").with(eq(new Long(assetAccessRequestStatusId)));
        assetAccessRequestStatusManager.removeAssetAccessRequestStatus(assetAccessRequestStatusId);
        assetAccessRequestStatusDao.verify();

        // reset expectations
        assetAccessRequestStatusDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(AssetAccessRequestStatus.class, assetAccessRequestStatus.getId());
        assetAccessRequestStatusDao.expects(once()).method("removeAssetAccessRequestStatus").isVoid();
        assetAccessRequestStatusDao.expects(once()).method("getAssetAccessRequestStatus").will(throwException(ex));
        assetAccessRequestStatusManager.removeAssetAccessRequestStatus(assetAccessRequestStatusId);
        try {
            assetAccessRequestStatusManager.getAssetAccessRequestStatus(assetAccessRequestStatusId);
            fail("AssetAccessRequestStatus with identifier '" + assetAccessRequestStatusId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        assetAccessRequestStatusDao.verify();
    }
}
