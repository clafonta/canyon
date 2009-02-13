
package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.model.AssetRole;


public interface AssetRoleManager extends Manager {
    /**
     * Retrieves all of the assetRoles
     */
    public List<AssetRole> getAssetRoles(AssetRole assetRole);

    /**
     * Gets assetRole's information based on id.
     * @param id the assetRole's id
     * @return assetRole populated assetRole object
     */
    public AssetRole getAssetRole(final String id);

    /**
     * Saves a assetRole's information
     * @param assetRole the object to be saved
     */
    public void saveAssetRole(AssetRole assetRole);

    /**
     * Removes a assetRole from the database by id
     * @param id the assetRole's id
     */
    public void removeAssetRole(final String id);
}

