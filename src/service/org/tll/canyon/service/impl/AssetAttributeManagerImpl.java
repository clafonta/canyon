
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetAttributeDao;
import org.tll.canyon.model.AssetAttribute;
import org.tll.canyon.service.AssetAttributeManager;



public class AssetAttributeManagerImpl extends BaseManager implements AssetAttributeManager {
    private AssetAttributeDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAssetAttributeDao(AssetAttributeDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.AssetAttributeManager#getAssetAttributes(org.tll.canyon.model.AssetAttribute)
     */
    public List<AssetAttribute> getAssetAttributes(final AssetAttribute assetAttribute) {
        return dao.getAssetAttributes(assetAttribute);
    }

    /**
     * @see org.tll.canyon.service.AssetAttributeManager#getAssetAttribute(String id)
     */
    public AssetAttribute getAssetAttribute(final String id) {
        return dao.getAssetAttribute(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.AssetAttributeManager#saveAssetAttribute(AssetAttribute assetAttribute)
     */
    public void saveAssetAttribute(AssetAttribute assetAttribute) {
        dao.saveAssetAttribute(assetAttribute);
    }

    /**
     * @see org.tll.canyon.service.AssetAttributeManager#removeAssetAttribute(String id)
     */
    public void removeAssetAttribute(final String id) {
        dao.removeAssetAttribute(new Long(id));
    }
}
