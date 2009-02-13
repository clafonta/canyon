
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;


public interface AssetAccessRequestDao extends Dao {

    /**
     * Retrieves all of the assetAccessRequests
     */
    public List<AssetAccessRequest> getAssetAccessRequests(AssetAccessRequest assetAccessRequest);

    /**
     * Gets assetAccessRequest's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the assetAccessRequest's id
     * @return assetAccessRequest populated assetAccessRequest object
     */
    public AssetAccessRequest getAssetAccessRequest(final Long id);

    /**
     * Saves a assetAccessRequest's information
     * @param assetAccessRequest the object to be saved
     */    
    public void saveAssetAccessRequest(AssetAccessRequest assetAccessRequest);

    /**
     * Removes a assetAccessRequest from the database by id
     * @param id the assetAccessRequest's id
     */
    public void removeAssetAccessRequest(final Long id);
    
    /**
     * Retrieves all of the <code>AssetAccessRequest</code> items that meet search criteria. 
     * 
     *  @param assetHitStatSearchForm contains search criteria.
     */
    public List<AssetAccessRequest> getAssetAccessRequestsBySearch(AssetAccessRequestSearchForm ssetAccessRequestSearchForm);
     
}

