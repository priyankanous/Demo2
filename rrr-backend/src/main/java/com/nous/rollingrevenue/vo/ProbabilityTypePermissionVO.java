package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ProbabilityTypePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long probabilityTypePermissionId;

	private boolean probabilityTypePermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getProbabilityTypePermissionId() {
		return probabilityTypePermissionId;
	}

	public void setProbabilityTypePermissionId(Long probabilityTypePermissionId) {
		this.probabilityTypePermissionId = probabilityTypePermissionId;
	}

	public boolean isProbabilityTypePermissionAll() {
		return probabilityTypePermissionAll;
	}

	public void setProbabilityTypePermissionAll(boolean probabilityTypePermissionAll) {
		this.probabilityTypePermissionAll = probabilityTypePermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
