
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetTypeDao;
import org.tll.canyon.model.AssetType;
import org.tll.canyon.service.AssetTypeManager;



public class AssetTypeManagerImpl extends BaseManager implements AssetTypeManager {
    private AssetTypeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAssetTypeDao(AssetTypeDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.AssetTypeManager#getAssetTypes(org.tll.canyon.model.AssetType)
     */
    public List<AssetType> getAssetTypes(final AssetType assetType) {
        return dao.getAssetTypes(assetType);
    }

    /**
     * @see org.tll.canyon.service.AssetTypeManager#getAssetType(String id)
     */
    public AssetType getAssetType(final String id) {
        return dao.getAssetType(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.AssetTypeManager#saveAssetType(AssetType assetType)
     */
    public void saveAssetType(AssetType assetType) {
        dao.saveAssetType(assetType);
    }

    /**
     * @see org.tll.canyon.service.AssetTypeManager#removeAssetType(String id)
     */
    public void removeAssetType(final String id) {
        dao.removeAssetType(new Long(id));
    }
}
