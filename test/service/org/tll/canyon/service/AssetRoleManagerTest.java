
package org.tll.canyon.service;

import java.util.List;
import java.util.ArrayList;


import org.jmock.Mock;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetRoleDao;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.service.BaseManagerTestCase;
import org.tll.canyon.service.impl.AssetRoleManagerImpl;

public class AssetRoleManagerTest extends BaseManagerTestCase {
    private final String assetRoleId = "1";
    private AssetRoleManagerImpl assetRoleManager = new AssetRoleManagerImpl();
    private Mock assetRoleDao = null;

    protected void setUp() throws Exception {
        super.setUp();
        assetRoleDao = new Mock(AssetRoleDao.class);
        assetRoleManager.setAssetRoleDao((AssetRoleDao) assetRoleDao.proxy());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        assetRoleManager = null;
    }

    public void testGetAssetRoles() throws Exception {
        List results = new ArrayList();
        AssetRole assetRole = new AssetRole();
        results.add(assetRole);

        // set expected behavior on dao
        assetRoleDao.expects(once()).method("getAssetRoles")
            .will(returnValue(results));

        List assetRoles = assetRoleManager.getAssetRoles(null);
        assertTrue(assetRoles.size() == 1);
        assetRoleDao.verify();
    }

    public void testGetAssetRole() throws Exception {
        // set expected behavior on dao
        assetRoleDao.expects(once()).method("getAssetRole")
            .will(returnValue(new AssetRole()));
        AssetRole assetRole = assetRoleManager.getAssetRole(assetRoleId);
        assertTrue(assetRole != null);
        assetRoleDao.verify();
    }

    public void testSaveAssetRole() throws Exception {
        AssetRole assetRole = new AssetRole();

        // set expected behavior on dao
        assetRoleDao.expects(once()).method("saveAssetRole")
            .with(same(assetRole)).isVoid();

        assetRoleManager.saveAssetRole(assetRole);
        assetRoleDao.verify();
    }

    public void testAddAndRemoveAssetRole() throws Exception {
        AssetRole assetRole = new AssetRole();

        // set required fields

        // set expected behavior on dao
        assetRoleDao.expects(once()).method("saveAssetRole")
            .with(same(assetRole)).isVoid();
        assetRoleManager.saveAssetRole(assetRole);
        assetRoleDao.verify();

        // reset expectations
        assetRoleDao.reset();

        assetRoleDao.expects(once()).method("removeAssetRole").with(eq(new Long(assetRoleId)));
        assetRoleManager.removeAssetRole(assetRoleId);
        assetRoleDao.verify();

        // reset expectations
        assetRoleDao.reset();
        // remove
        Exception ex = new ObjectRetrievalFailureException(AssetRole.class, assetRole.getId());
        assetRoleDao.expects(once()).method("removeAssetRole").isVoid();
        assetRoleDao.expects(once()).method("getAssetRole").will(throwException(ex));
        assetRoleManager.removeAssetRole(assetRoleId);
        try {
            assetRoleManager.getAssetRole(assetRoleId);
            fail("AssetRole with identifier '" + assetRoleId + "' found in database");
        } catch (ObjectRetrievalFailureException e) {
            assertNotNull(e.getMessage());
        }
        assetRoleDao.verify();
    }
}
