package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RegionViewPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long regionViewPermissionId;

	private boolean regionViewPermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getRegionViewPermissionId() {
		return regionViewPermissionId;
	}

	public void setRegionViewPermissionId(Long regionViewPermissionId) {
		this.regionViewPermissionId = regionViewPermissionId;
	}

	public boolean isRegionViewPermissionAll() {
		return regionViewPermissionAll;
	}

	public void setRegionViewPermissionAll(boolean regionViewPermissionAll) {
		this.regionViewPermissionAll = regionViewPermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
