
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.AssetAttribute;


public interface AssetAttributeManager extends Manager {
    /**
     * Retrieves all of the assetAttributes
     */
    public List<AssetAttribute> getAssetAttributes(AssetAttribute assetAttribute);

    /**
     * Gets assetAttribute's information based on id.
     * @param id the assetAttribute's id
     * @return assetAttribute populated assetAttribute object
     */
    public AssetAttribute getAssetAttribute(final String id);

    /**
     * Saves a assetAttribute's information
     * @param assetAttribute the object to be saved
     */
    public void saveAssetAttribute(AssetAttribute assetAttribute);

    /**
     * Removes a assetAttribute from the database by id
     * @param id the assetAttribute's id
     */
    public void removeAssetAttribute(final String id);
}

