package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class SBUClientViewPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sbuClientViewPermissionId;

	private boolean sbuClientViewPermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getSbuClientViewPermissionId() {
		return sbuClientViewPermissionId;
	}

	public void setSbuClientViewPermissionId(Long sbuClientViewPermissionId) {
		this.sbuClientViewPermissionId = sbuClientViewPermissionId;
	}

	public boolean isSbuClientViewPermissionAll() {
		return sbuClientViewPermissionAll;
	}

	public void setSbuClientViewPermissionAll(boolean sbuClientViewPermissionAll) {
		this.sbuClientViewPermissionAll = sbuClientViewPermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
