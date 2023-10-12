package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class WorkOrderPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long workOrderPermissionId;

	private boolean workOrderPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getWorkOrderPermissionId() {
		return workOrderPermissionId;
	}

	public void setWorkOrderPermissionId(Long workOrderPermissionId) {
		this.workOrderPermissionId = workOrderPermissionId;
	}

	public boolean isWorkOrderPermissionAll() {
		return workOrderPermissionAll;
	}

	public void setWorkOrderPermissionAll(boolean workOrderPermissionAll) {
		this.workOrderPermissionAll = workOrderPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
