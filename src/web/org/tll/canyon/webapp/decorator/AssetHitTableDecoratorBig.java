package org.tll.canyon.webapp.decorator;

import org.displaytag.decorator.TableDecorator;
import org.tll.canyon.model.AssetHitStat;


public class AssetHitTableDecoratorBig extends TableDecorator {

    private String BASE_FILTER_URL = new String("<a href=\"assetHitStatList.html?view=detailonly&from=list");

    public String getUserIdentifierConnectingToAssetLink() {
        AssetHitStat rowObject = (AssetHitStat) getCurrentRowObject();
        AssetHitStat filter = (AssetHitStat) this.getPageContext().getRequest().getAttribute("assetHitStat");
        StringBuffer filterURL = new StringBuffer(BASE_FILTER_URL);
        if( filter.getId()!=null ){
            // do Nothing
        }else {
            filterURL.append("&userIdentifierConnectingToAsset=" + rowObject.getUserIdentifierConnectingToAsset());
        }
        String result = appendFilterInfo(filterURL.toString());
        return (result +"\">" + rowObject.getUserIdentifierConnectingToAsset() + "</a>");     
    }

    public String getConnectingIPLink() {
        AssetHitStat filter = (AssetHitStat) this.getPageContext().getRequest().getAttribute("assetHitStat");
        AssetHitStat rowObject = (AssetHitStat) getCurrentRowObject();
        StringBuffer filterURL = new StringBuffer(BASE_FILTER_URL);
        if (filter.getConnectingIP() != null && filter.getConnectingIP().trim().length() > 0) {
            //Do nothing. 
        } else {
            filterURL.append("&connectingIP=" + rowObject.getConnectingIP());
        }
        String result = appendFilterInfo(filterURL.toString());
        return (result +"\">" + rowObject.getConnectingIP() + "</a>");     

    }
    
    public String getApplicationUsedToConnectLink() {
        AssetHitStat filter = (AssetHitStat) this.getPageContext().getRequest().getAttribute("assetHitStat");
        AssetHitStat rowObject = (AssetHitStat) getCurrentRowObject();
        StringBuffer filterURL = new StringBuffer(BASE_FILTER_URL);
        if (filter.getApplicationUsedToConnect() != null && filter.getApplicationUsedToConnect().trim().length() > 0) {
            //Do nothing. 
        } else {
            filterURL.append("&applicationUsedToConnect=" + rowObject.getApplicationUsedToConnect());
        }
        String result = appendFilterInfo(filterURL.toString());
        return (result +"\">" + rowObject.getApplicationUsedToConnect() + "</a>");     

    }

    private String appendFilterInfo(String arg) {
        StringBuffer filterURL = new StringBuffer(arg);
        AssetHitStat filter = (AssetHitStat) this.getPageContext().getRequest().getAttribute("assetHitStat");
        AssetHitStat rowObject = (AssetHitStat) getCurrentRowObject();
        filterURL.append("&id=" + rowObject.getId());
        filterURL.append("&assetDetailId=" + filter.getAssetDetail().getId());
        if (filter.getConnectingIP() != null && filter.getConnectingIP().trim().length() > 0) {
            filterURL.append("&connectingIP=" + filter.getConnectingIP());
        }

        if (filter.getUserIdentifierConnectingToAsset() != null
                && filter.getUserIdentifierConnectingToAsset().trim().length() > 0) {
            filterURL.append("&userIdentifierConnectingToAsset=" + filter.getUserIdentifierConnectingToAsset());
        }
        if (filter.getApplicationUsedToConnect() != null && filter.getApplicationUsedToConnect().trim().length() > 0) {
            filterURL.append("&applicationUsedToConnect=" + filter.getApplicationUsedToConnect());
        }
        if( filter.getId()!=null ){
            filterURL.append("&id=" + filter.getId());
        }
        return filterURL.toString();

    }
}
