package org.tll.canyon.dao;

import java.util.List;


import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetAccessRequestStatusDao;
import org.tll.canyon.dao.BaseDaoTestCase;
import org.tll.canyon.model.AssetAccessRequestStatus;

public class AssetAccessRequestStatusDaoTest extends BaseDaoTestCase {
    private Long assetAccessRequestStatusId = new Long("1");
    private AssetAccessRequestStatusDao dao = null;

    public void setAssetAccessRequestStatusDao(AssetAccessRequestStatusDao dao) {
        this.dao = dao;
    }

    public void testAddAssetAccessRequestStatus() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();

        // set required fields

        dao.saveAssetAccessRequestStatus(assetAccessRequestStatus);

        // verify a primary key was assigned
        assertNotNull(assetAccessRequestStatus.getId());

        // verify set fields are same after save
    }

    public void testGetAssetAccessRequestStatus() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = dao.getAssetAccessRequestStatus(assetAccessRequestStatusId);
        assertNotNull(assetAccessRequestStatus);
    }

    public void testGetAssetAccessRequestStatuss() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = new AssetAccessRequestStatus();

        List results = dao.getAssetAccessRequestStatusList(assetAccessRequestStatus);
        assertTrue(results.size() > 0);
    }

    public void testSaveAssetAccessRequestStatus() throws Exception {
        AssetAccessRequestStatus assetAccessRequestStatus = dao.getAssetAccessRequestStatus(assetAccessRequestStatusId);

        // update required fields

        dao.saveAssetAccessRequestStatus(assetAccessRequestStatus);

    }

    public void testRemoveAssetAccessRequestStatus() throws Exception {
        Long removeId = new Long("3");
        dao.removeAssetAccessRequestStatus(removeId);
        try {
            dao.getAssetAccessRequestStatus(removeId);
            fail("assetAccessRequestStatus found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) { // Spring 2.0 throws this one
            assertNotNull(e.getMessage());        	
        }
    }
}
