package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BDMPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long bdmPermissionId;

	private boolean bdmPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getBdmPermissionId() {
		return bdmPermissionId;
	}

	public void setBdmPermissionId(Long bdmPermissionId) {
		this.bdmPermissionId = bdmPermissionId;
	}

	public boolean isBdmPermissionAll() {
		return bdmPermissionAll;
	}

	public void setBdmPermissionAll(boolean bdmPermissionAll) {
		this.bdmPermissionAll = bdmPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
