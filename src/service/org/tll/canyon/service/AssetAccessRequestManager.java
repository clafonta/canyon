
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.AssetAccessRequest;
import org.tll.canyon.model.viewwrappers.AssetAccessRequestSearchForm;


public interface AssetAccessRequestManager extends Manager {
    
    /**
     * Retrieves all of the <code>AssetAccessRequest</code> items with matching attributes 
     * of assetAccessRequet. Example: if same ID, if same ID and name, etc. 
     * 
     * @param example if null, then returns all <code>AssetAccessRequest</code> items from 
     * the data store. Otherwise, returns only items with matching non-null attributes  
     */
    public List<AssetAccessRequest> getAssetAccessRequests(AssetAccessRequest example);

    /**
     * Gets assetAccessRequest's information based on id.
     * @param id the assetAccessRequest's id
     * @return assetAccessRequest populated assetAccessRequest object
     */
    public AssetAccessRequest getAssetAccessRequest(final String id);

    /**
     * Saves a assetAccessRequest's information
     * @param assetAccessRequest the object to be saved
     */
    public void saveAssetAccessRequest(AssetAccessRequest assetAccessRequest);

    /**
     * Removes a assetAccessRequest from the database by id
     * @param id the assetAccessRequest's id
     */
    public void removeAssetAccessRequest(final String id);
    
    /**
     * Retrieves all of the <code>AssetAccessRequest</code> items that meet search criteria. 
     * 
     *  @param assetHitStatSearchForm contains search criteria.
     */
    public List<AssetAccessRequest> getAssetAccessRequestsBySearch(AssetAccessRequestSearchForm assetAccessRequestSearchForm);

     
}

