package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BusinessUnitViewPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long businessUnitViewPermissionId;

	private boolean businessUnitViewPermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getBusinessUnitViewPermissionId() {
		return businessUnitViewPermissionId;
	}

	public void setBusinessUnitViewPermissionId(Long businessUnitViewPermissionId) {
		this.businessUnitViewPermissionId = businessUnitViewPermissionId;
	}

	public boolean isBusinessUnitViewPermissionAll() {
		return businessUnitViewPermissionAll;
	}

	public void setBusinessUnitViewPermissionAll(boolean businessUnitViewPermissionAll) {
		this.businessUnitViewPermissionAll = businessUnitViewPermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
