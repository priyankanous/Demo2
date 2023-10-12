package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BusinessUnitPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long businessUnitPermissionId;

	private boolean businessUnitPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getBusinessUnitPermissionId() {
		return businessUnitPermissionId;
	}

	public void setBusinessUnitPermissionId(Long businessUnitPermissionId) {
		this.businessUnitPermissionId = businessUnitPermissionId;
	}

	public boolean isBusinessUnitPermissionAll() {
		return businessUnitPermissionAll;
	}

	public void setBusinessUnitPermissionAll(boolean businessUnitPermissionAll) {
		this.businessUnitPermissionAll = businessUnitPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
