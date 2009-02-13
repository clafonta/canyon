package org.tll.canyon.service;

import java.util.List;

import org.tll.canyon.dao.AssetHistoryNoteDao;
import org.tll.canyon.model.AssetHistoryNote;


public interface AssetHistoryNoteManager {
    public void setAssetHistoryNoteDao(AssetHistoryNoteDao dao);
    public AssetHistoryNote getAssetHistoryNote(Long assetHistoryNoteId);
    public void saveAssetHistoryNote(AssetHistoryNote assetHistoryNote);
    public void removeAssetHistoryNote(Long assetHistoryNote);
    public List<AssetHistoryNote> getAssetHistoryNotes(Long assetDetailId);
}

