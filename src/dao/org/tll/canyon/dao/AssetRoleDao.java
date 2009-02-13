
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.dao.Dao;
import org.tll.canyon.model.AssetRole;


public interface AssetRoleDao extends Dao {

    /**
     * Retrieves all of the assetRoles
     */
    public List<AssetRole> getAssetRoles(AssetRole assetRole);

    /**
     * Gets assetRole's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the assetRole's id
     * @return assetRole populated assetRole object
     */
    public AssetRole getAssetRole(final Long id);

    /**
     * Saves a assetRole's information
     * @param assetRole the object to be saved
     */    
    public void saveAssetRole(AssetRole assetRole);

    /**
     * Removes a assetRole from the database by id
     * @param id the assetRole's id
     */
    public void removeAssetRole(final Long id);
}

