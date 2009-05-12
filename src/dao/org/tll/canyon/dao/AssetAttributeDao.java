
package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetAttribute;


public interface AssetAttributeDao extends Dao {

  
    public List<AssetAttribute> getAssetAttributes(AssetAttribute assetAttribute);

   
    public AssetAttribute getAssetAttribute(final Long id);

       
    public void saveAssetAttribute(AssetAttribute assetAttribute);

    
    public void removeAssetAttribute(final Long id);
}

