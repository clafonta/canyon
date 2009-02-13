package org.tll.canyon.model.viewwrappers;

import org.tll.canyon.model.AssetRole;

public class AssetRoleInfo {

    private String name;
    private String description;
    private String id;
    public AssetRoleInfo(){};
    public AssetRoleInfo(AssetRole assetRole){
        this.name = assetRole.getName();
        this.id = assetRole.getId().toString();
        this.description = assetRole.getDescription();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
