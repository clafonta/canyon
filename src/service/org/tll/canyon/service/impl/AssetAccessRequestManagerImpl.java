
package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetAccessRequestDao;
import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;
import org.tll.canyon.service.AssetAccessRequestManager;


public class AssetAccessRequestManagerImpl extends BaseManager implements AssetAccessRequestManager {
    private AssetAccessRequestDao dao;

    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setAssetAccessRequestDao(AssetAccessRequestDao dao) {
        this.dao = dao;
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestManager#getAssetAccessRequests(org.tll.canyon.model.AssetAccessRequest)
     */
    public List<AssetAccessRequest> getAssetAccessRequests(final AssetAccessRequest assetAccessRequest) {
        return dao.getAssetAccessRequests(assetAccessRequest);
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestManager#getAssetAccessRequest(String id)
     */
    public AssetAccessRequest getAssetAccessRequest(final String id) {
        return dao.getAssetAccessRequest(new Long(id));
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestManager#saveAssetAccessRequest(AssetAccessRequest assetAccessRequest)
     */
    public void saveAssetAccessRequest(AssetAccessRequest assetAccessRequest) {
        dao.saveAssetAccessRequest(assetAccessRequest);
    }

    /**
     * @see org.tll.canyon.service.AssetAccessRequestManager#removeAssetAccessRequest(String id)
     */
    public void removeAssetAccessRequest(final String id) {
        dao.removeAssetAccessRequest(new Long(id));
    }    
    
    public List<AssetAccessRequest> getAssetAccessRequestsBySearch(AssetAccessRequestSearchForm assetAccessRequestSearchForm){
        return dao.getAssetAccessRequestsBySearch(assetAccessRequestSearchForm);
    }
}
