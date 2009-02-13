package org.tll.canyon.webapp.decorator;

import org.displaytag.decorator.TableDecorator;
import org.tll.canyon.model.AssetHitStat;


public class AssetHitTableDecorator extends TableDecorator {

    public String getUserIdentifierConnectingToAssetLink() {
               
        AssetHitStat object = (AssetHitStat) getCurrentRowObject();
        //int index = getListIndex();
        
        // assetHitStatList.html?id=0&assetDetailId=1&view=detailonly&from=list
        return "<a href=\"assetHitStatList.html?id=" //$NON-NLS-1$
                + object.getId() + "\"" 
                + "&view=detailonly"
                + "&from=list"
                + object.getUserIdentifierConnectingToAsset() + "</a>"; //$NON-NLS-1$
    }
}
