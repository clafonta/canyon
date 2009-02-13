package org.tll.canyon.dao;

import java.util.List;

import org.tll.canyon.model.AssetDetail;

public interface CheckpointDao  extends Dao{

	public List<AssetDetail> getAssetsAssociatedWithNonActiveEmployees();
}
