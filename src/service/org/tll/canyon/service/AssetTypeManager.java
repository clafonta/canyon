
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.AssetType;


public interface AssetTypeManager extends Manager {
    /**
     * Retrieves all of the assetTypes
     */
    public List<AssetType> getAssetTypes(AssetType assetType);

    /**
     * Gets assetType's information based on id.
     * @param id the assetType's id
     * @return assetType populated assetType object
     */
    public AssetType getAssetType(final String id);

    /**
     * Saves a assetType's information
     * @param assetType the object to be saved
     */
    public void saveAssetType(AssetType assetType);

    /**
     * Removes a assetType from the database by id
     * @param id the assetType's id
     */
    public void removeAssetType(final String id);
}

