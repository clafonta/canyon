package org.tll.canyon.dao;

import java.util.List;


import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetRoleDao;
import org.tll.canyon.dao.BaseDaoTestCase;
import org.tll.canyon.model.AssetRole;

public class AssetRoleDaoTest extends BaseDaoTestCase {
    private Long assetRoleId = new Long("1");
    private AssetRoleDao dao = null;

    public void setAssetRoleDao(AssetRoleDao dao) {
        this.dao = dao;
    }

    public void testAddAssetRole() throws Exception {
        AssetRole assetRole = new AssetRole();

        // set required fields

        dao.saveAssetRole(assetRole);

        // verify a primary key was assigned
        assertNotNull(assetRole.getId());

        // verify set fields are same after save
    }

    public void testGetAssetRole() throws Exception {
        AssetRole assetRole = dao.getAssetRole(assetRoleId);
        assertNotNull(assetRole);
    }

    public void testGetAssetRoles() throws Exception {
        AssetRole assetRole = new AssetRole();

        List results = dao.getAssetRoles(assetRole);
        assertTrue(results.size() > 0);
    }

    public void testSaveAssetRole() throws Exception {
        AssetRole assetRole = dao.getAssetRole(assetRoleId);

        // update required fields

        dao.saveAssetRole(assetRole);

    }

    public void testRemoveAssetRole() throws Exception {
        Long removeId = new Long("3");
        dao.removeAssetRole(removeId);
        try {
            dao.getAssetRole(removeId);
            fail("assetRole found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        } catch (InvalidDataAccessApiUsageException e) { // Spring 2.0 throws this one
            assertNotNull(e.getMessage());        	
        }
    }
}
