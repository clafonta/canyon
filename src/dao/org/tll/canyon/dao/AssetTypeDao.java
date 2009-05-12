
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetType;


public interface AssetTypeDao extends Dao {

  
    public List<AssetType> getAssetTypes(AssetType assetType);

   
    public AssetType getAssetType(final Long id);

       
    public void saveAssetType(AssetType assetType);

    
    public void removeAssetType(final Long id);
}

