package org.tll.canyon.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.tll.canyon.dao.AssetHistoryNoteDao;
import org.tll.canyon.model.AssetHistoryNote;
import org.tll.canyon.model.AssetHitStat;


public class AssetHistoryNoteDaoHibernate extends BaseDaoHibernate implements AssetHistoryNoteDao {

	
	public AssetHistoryNote getAssetHistoryNote(final Long assetHistoryNoteId){
		
		AssetHistoryNote assetHistoryNote = (AssetHistoryNote) getHibernateTemplate().get(AssetHitStat.class, assetHistoryNoteId);
        if (assetHistoryNote == null) {
            throw new ObjectRetrievalFailureException(AssetHistoryNote.class, assetHistoryNoteId);
        }
        return assetHistoryNote;
		
		
	}
    public void saveAssetHistoryNote(AssetHistoryNote assetHistoryNote){
    	getHibernateTemplate().saveOrUpdate(assetHistoryNote);
    }
    public void removeAssetHistoryNote(Long assetHistoryNoteId){
    	getHibernateTemplate().delete(getAssetHistoryNote(assetHistoryNoteId));
    }
    
    @SuppressWarnings("unchecked")
	public List<AssetHistoryNote> getAssetHistoryNotes(Long assetDetailId){
    	List<AssetHistoryNote> items = getHibernateTemplate().find("from AssetHistoryNote where assetDetailId=?", assetDetailId);
        if (items == null || items.isEmpty()) {
            return new ArrayList<AssetHistoryNote>();
        } else {
            return items;
        }
    }
}
