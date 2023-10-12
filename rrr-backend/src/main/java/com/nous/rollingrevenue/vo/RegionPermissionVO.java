package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RegionPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long regionPermissionId;

	private boolean regionPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getRegionPermissionId() {
		return regionPermissionId;
	}

	public void setRegionPermissionId(Long regionPermissionId) {
		this.regionPermissionId = regionPermissionId;
	}

	public boolean isRegionPermissionAll() {
		return regionPermissionAll;
	}

	public void setRegionPermissionAll(boolean regionPermissionAll) {
		this.regionPermissionAll = regionPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
