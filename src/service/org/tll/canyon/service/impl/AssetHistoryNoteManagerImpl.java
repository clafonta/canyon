package org.tll.canyon.service.impl;

import java.util.List;

import org.tll.canyon.dao.AssetHistoryNoteDao;
import org.tll.canyon.model.AssetHistoryNote;
import org.tll.canyon.service.AssetHistoryNoteManager;

public class AssetHistoryNoteManagerImpl implements AssetHistoryNoteManager {

	private AssetHistoryNoteDao assetHistoryNoteDao; 
	public AssetHistoryNote getAssetHistoryNote(Long assetHistoryNoteId) {
		return this.assetHistoryNoteDao.getAssetHistoryNote(assetHistoryNoteId);
	}

	public List<AssetHistoryNote> getAssetHistoryNotes(Long assetDetailId) {
		return this.assetHistoryNoteDao.getAssetHistoryNotes(assetDetailId);
	}

	public void removeAssetHistoryNote(Long assetHistoryNote) {
		this.assetHistoryNoteDao.removeAssetHistoryNote(assetHistoryNote);

	}

	public void saveAssetHistoryNote(AssetHistoryNote assetHistoryNote) {
		this.assetHistoryNoteDao.saveAssetHistoryNote(assetHistoryNote);

	}

	public void setAssetHistoryNoteDao(AssetHistoryNoteDao dao) {
		this.assetHistoryNoteDao = dao;

	}

}
