package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class GlobalLeaveLassFactorPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long globalLeaveLassFactorPermissionId;

	private boolean globalLeaveLassFactorPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getGlobalLeaveLassFactorPermissionId() {
		return globalLeaveLassFactorPermissionId;
	}

	public void setGlobalLeaveLassFactorPermissionId(Long globalLeaveLassFactorPermissionId) {
		this.globalLeaveLassFactorPermissionId = globalLeaveLassFactorPermissionId;
	}

	public boolean isGlobalLeaveLassFactorPermissionAll() {
		return globalLeaveLassFactorPermissionAll;
	}

	public void setGlobalLeaveLassFactorPermissionAll(boolean globalLeaveLassFactorPermissionAll) {
		this.globalLeaveLassFactorPermissionAll = globalLeaveLassFactorPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
