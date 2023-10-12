package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BusinessTypeViewPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long businessTypeViewPermissionId;

	private boolean businessTypeViewPermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getBusinessTypeViewPermissionId() {
		return businessTypeViewPermissionId;
	}

	public void setBusinessTypeViewPermissionId(Long businessTypeViewPermissionId) {
		this.businessTypeViewPermissionId = businessTypeViewPermissionId;
	}

	public boolean isBusinessTypeViewPermissionAll() {
		return businessTypeViewPermissionAll;
	}

	public void setBusinessTypeViewPermissionAll(boolean businessTypeViewPermissionAll) {
		this.businessTypeViewPermissionAll = businessTypeViewPermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
