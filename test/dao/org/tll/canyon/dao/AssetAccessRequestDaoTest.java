package org.tll.canyon.dao;

import java.util.List;


import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetAccessRequestDao;
import org.tll.canyon.dao.BaseDaoTestCase;
import org.tll.canyon.model.AssetAccessRequest;

public class AssetAccessRequestDaoTest extends BaseDaoTestCase {
    private Long assetAccessRequestId = new Long("1");
    private AssetAccessRequestDao dao = null;

    public void setAssetAccessRequestDao(AssetAccessRequestDao dao) {
        this.dao = dao;
    }

    public void testAddAssetAccessRequest() throws Exception {
        AssetAccessRequest assetAccessRequest = new AssetAccessRequest();

        // set required fields

        dao.saveAssetAccessRequest(assetAccessRequest);

        // verify a primary key was assigned
        assertNotNull(assetAccessRequest.getId());

        // verify set fields are same after save
    }

    public void testGetAssetAccessRequest() throws Exception {
        AssetAccessRequest assetAccessRequest = dao.getAssetAccessRequest(assetAccessRequestId);
        assertNotNull(assetAccessRequest);
    }

    public void testGetAssetAccessRequests() throws Exception {
        AssetAccessRequest assetAccessRequest = new AssetAccessRequest();

        List results = dao.getAssetAccessRequests(assetAccessRequest);
        assertTrue(results.size() > 0);
    }

    public void testSaveAssetAccessRequest() throws Exception {
        AssetAccessRequest assetAccessRequest = dao.getAssetAccessRequest(assetAccessRequestId);

        // update required fields

        dao.saveAssetAccessRequest(assetAccessRequest);

    }

    public void testRemoveAssetAccessRequest() throws Exception {
        Long removeId = new Long("3");
        dao.removeAssetAccessRequest(removeId);
        try {
            dao.getAssetAccessRequest(removeId);
            fail("assetAccessRequest found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) { // Spring 2.0 throws this one
            assertNotNull(e.getMessage());        	
        }
    }
}
