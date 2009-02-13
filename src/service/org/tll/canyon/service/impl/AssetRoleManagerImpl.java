
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetRoleDao;
import org.tll.canyon.model.AssetRole;
import org.tll.canyon.service.AssetRoleManager;
import org.tll.canyon.service.impl.BaseManager;


public class AssetRoleManagerImpl extends BaseManager implements AssetRoleManager {
    private AssetRoleDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAssetRoleDao(AssetRoleDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.AssetRoleManager#getAssetRoles(org.tll.canyon.model.AssetRole)
     */
    public List<AssetRole> getAssetRoles(final AssetRole assetRole) {
        return dao.getAssetRoles(assetRole);
    }

    /**
     * @see org.tll.canyon.service.AssetRoleManager#getAssetRole(String id)
     */
    public AssetRole getAssetRole(final String id) {
        return dao.getAssetRole(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.AssetRoleManager#saveAssetRole(AssetRole assetRole)
     */
    public void saveAssetRole(AssetRole assetRole) {
        dao.saveAssetRole(assetRole);
    }

    /**
     * @see org.tll.canyon.service.AssetRoleManager#removeAssetRole(String id)
     */
    public void removeAssetRole(final String id) {
        dao.removeAssetRole(new Long(id));
    }
}
