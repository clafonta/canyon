
package org.tll.canyon.service;

import java.util.List;
import java.util.Locale;

import org.tll.canyon.model.AssetAccessRequestStatus;


public interface AssetAccessRequestStatusManager extends Manager {
    /**
     * Retrieves all of the assetAccessRequestStatuss
     */
    public List<AssetAccessRequestStatus> getAssetAccessRequestStatusList(AssetAccessRequestStatus assetAccessRequestStatus);

    /**
     * Gets assetAccessRequestStatus's information based on id.
     * @param id the assetAccessRequestStatus's id
     * @return assetAccessRequestStatus populated assetAccessRequestStatus object
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatus(final String id);
    
    /**
     * Gets assetAccessRequestStatus's information based on track ID
     * @param trackId
     * @see AssetAccessRequestStatus#getApprovalEmailUniqueTrackId()
     * @return assetAccessRequestStatus populated assetAccessRequestStatus object
     */
    public AssetAccessRequestStatus getAssetAccessRequestStatusByTrackId(final String trackId);


    /**
     * Saves a assetAccessRequestStatus's information
     * @param assetAccessRequestStatus the object to be saved
     */
    public void saveAssetAccessRequestStatus(AssetAccessRequestStatus assetAccessRequestStatus);

    /**
     * Removes a assetAccessRequestStatus from the database by id
     * @param id the assetAccessRequestStatus's id
     */
    public void removeAssetAccessRequestStatus(final String id);
    
    /**
     * Processes asset access requests, updating state, sends out email notifications, etc.  
     * 
     * @param assetAccessRequest
     * @see AssetAccessRequestStatus#getApprovalEmailUniqueTrackId()
     * @return
     */
    public void processAssetAccessRequestState(String assetAccessRequestId, Locale locale);
}

