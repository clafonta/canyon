
package org.tll.canyon.service;

import java.util.List;
import java.util.ArrayList;


import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetAccessRequestDao;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.service.BaseManagerTestCase;
import org.tll.canyon.service.impl.AssetAccessRequestManagerImpl;

public class AssetAccessRequestManagerTest extends BaseManagerTestCase {
    private final String assetAccessRequestId = "1";
    private AssetAccessRequestManagerImpl assetAccessRequestManager = new AssetAccessRequestManagerImpl();
    private Mock assetAccessRequestDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        assetAccessRequestDao = new Mock(AssetAccessRequestDao.class);
        assetAccessRequestManager.setAssetAccessRequestDao((AssetAccessRequestDao) assetAccessRequestDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        assetAccessRequestManager = null;
    }

    public void testGetAssetAccessRequests() throws Exception {
        List results = new ArrayList();
        AssetAccessRequest assetAccessRequest = new AssetAccessRequest();
        results.add(assetAccessRequest);

        // set expected behavior on dao
        assetAccessRequestDao.expects(once()).method("getAssetAccessRequests")
            .will(returnValue(results));

        List assetAccessRequests = assetAccessRequestManager.getAssetAccessRequests(null);
        assertTrue(assetAccessRequests.size() == 1);
        assetAccessRequestDao.verify();
    }

    public void testGetAssetAccessRequest() throws Exception {
        // set expected behavior on dao
        assetAccessRequestDao.expects(once()).method("getAssetAccessRequest")
            .will(returnValue(new AssetAccessRequest()));
        AssetAccessRequest assetAccessRequest = assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
        assertTrue(assetAccessRequest != null);
        assetAccessRequestDao.verify();
    }

    public void testSaveAssetAccessRequest() throws Exception {
        AssetAccessRequest assetAccessRequest = new AssetAccessRequest();

        // set expected behavior on dao
        assetAccessRequestDao.expects(once()).method("saveAssetAccessRequest")
            .with(same(assetAccessRequest)).isVoid();

        assetAccessRequestManager.saveAssetAccessRequest(assetAccessRequest);
        assetAccessRequestDao.verify();
    }

    public void testAddAndRemoveAssetAccessRequest() throws Exception {
        AssetAccessRequest assetAccessRequest = new AssetAccessRequest();

        // set required fields

        // set expected behavior on dao
        assetAccessRequestDao.expects(once()).method("saveAssetAccessRequest")
            .with(same(assetAccessRequest)).isVoid();
        assetAccessRequestManager.saveAssetAccessRequest(assetAccessRequest);
        assetAccessRequestDao.verify();

        // reset expectations
        assetAccessRequestDao.reset();

        assetAccessRequestDao.expects(once()).method("removeAssetAccessRequest").with(eq(new Long(assetAccessRequestId)));
        assetAccessRequestManager.removeAssetAccessRequest(assetAccessRequestId);
        assetAccessRequestDao.verify();

        // reset expectations
        assetAccessRequestDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(AssetAccessRequest.class, assetAccessRequest.getId());
        assetAccessRequestDao.expects(once()).method("removeAssetAccessRequest").isVoid();
        assetAccessRequestDao.expects(once()).method("getAssetAccessRequest").will(throwException(ex));
        assetAccessRequestManager.removeAssetAccessRequest(assetAccessRequestId);
        try {
            assetAccessRequestManager.getAssetAccessRequest(assetAccessRequestId);
            fail("AssetAccessRequest with identifier '" + assetAccessRequestId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        assetAccessRequestDao.verify();
    }
}
