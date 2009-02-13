package org.tll.canyon.dao.hibernate;

import java.util.List;

import org.tll.canyon.dao.CheckpointDao;
import org.tll.canyon.model.AssetDetail;

public class CheckpointDaoHibernate extends BaseDaoHibernate implements
		CheckpointDao {

	@SuppressWarnings("unchecked")
	public List<AssetDetail> getAssetsAssociatedWithNonActiveEmployees(){
		
		/**
		 SELECT a.id FROM asset_detail a, hr_emp_manager_info h 
WHERE 
(h.EMP_USERID = a.primary_owner_emp_id
OR
h.EMP_USERID = a.secondary_owner_emp_id
OR
h.EMP_USERID = a.primary_admin_emp_id
OR 
h.EMP_USERID = a.secondary_admin_emp_id
) AND h.EMP_STATUS = 'N' AND h.EMP_USERID = 'UID_4'


UPDATE asset_detail a
SET a.primary_admin_emp_id = 'UID_4'
WHERE a.asset_name LIKE '%CAT%'
		 */
		return this.getHibernateTemplate().find("from AssetDetail");
		
	}


	
	

}
