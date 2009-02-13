
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.dao.Dao;
import org.tll.canyon.model.AssetAccessRequestStatus;


public interface AssetAccessRequestStatusDao extends Dao {

    /**
     * Retrieves all of the assetAccessRequestStatuss
     */
    public List<AssetAccessRequestStatus> getAssetAccessRequestStatusList(AssetAccessRequestStatus assetAccessRequestStatus);

    /**
     * Gets assetAccessRequestStatus's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the assetAccessRequestStatus's id
     * @return assetAccessRequestStatus populated assetAccessRequestStatus object
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatus(final Long id);

    /**
     * Saves a assetAccessRequestStatus's information
     * @param assetAccessRequestStatus the object to be saved
     */    
    public void saveAssetAccessRequestStatus(AssetAccessRequestStatus assetAccessRequestStatus);

    /**
     * Removes a assetAccessRequestStatus from the database by id
     * @param id the assetAccessRequestStatus's id
     */
    public void removeAssetAccessRequestStatus(final Long id);
}

