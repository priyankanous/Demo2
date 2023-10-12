package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BusinessTypePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long businessTypePermissionId;

	private boolean businessTypePermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getBusinessTypePermissionId() {
		return businessTypePermissionId;
	}

	public void setBusinessTypePermissionId(Long businessTypePermissionId) {
		this.businessTypePermissionId = businessTypePermissionId;
	}

	public boolean isBusinessTypePermissionAll() {
		return businessTypePermissionAll;
	}

	public void setBusinessTypePermissionAll(boolean businessTypePermissionAll) {
		this.businessTypePermissionAll = businessTypePermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
