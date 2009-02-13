package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetHistoryNote;


public interface AssetHistoryNoteDao extends Dao {
    public AssetHistoryNote getAssetHistoryNote(Long assetHistoryNoteId);
    public void saveAssetHistoryNote(AssetHistoryNote assetHistoryNote);
    public void removeAssetHistoryNote(Long assetHistoryNote);
    public List<AssetHistoryNote> getAssetHistoryNotes(Long assetDetailId);
    
}


