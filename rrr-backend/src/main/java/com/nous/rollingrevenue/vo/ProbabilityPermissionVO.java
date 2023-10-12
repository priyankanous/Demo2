package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ProbabilityPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long probabilityPermissionId;

	private boolean probabilityPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getProbabilityPermissionId() {
		return probabilityPermissionId;
	}

	public void setProbabilityPermissionId(Long probabilityPermissionId) {
		this.probabilityPermissionId = probabilityPermissionId;
	}

	public boolean isProbabilityPermissionAll() {
		return probabilityPermissionAll;
	}

	public void setProbabilityPermissionAll(boolean probabilityPermissionAll) {
		this.probabilityPermissionAll = probabilityPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
