package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class OpportunityPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long opportunityPermissionId;

	private boolean opportunityPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getOpportunityPermissionId() {
		return opportunityPermissionId;
	}

	public void setOpportunityPermissionId(Long opportunityPermissionId) {
		this.opportunityPermissionId = opportunityPermissionId;
	}

	public boolean isOpportunityPermissionAll() {
		return opportunityPermissionAll;
	}

	public void setOpportunityPermissionAll(boolean opportunityPermissionAll) {
		this.opportunityPermissionAll = opportunityPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
