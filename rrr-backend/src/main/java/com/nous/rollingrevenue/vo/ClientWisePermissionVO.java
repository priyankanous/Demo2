package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ClientWisePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long clientWisePermissionId;

	private boolean clientWisePermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getClientWisePermissionId() {
		return clientWisePermissionId;
	}

	public void setClientWisePermissionId(Long clientWisePermissionId) {
		this.clientWisePermissionId = clientWisePermissionId;
	}

	public boolean isClientWisePermissionAll() {
		return clientWisePermissionAll;
	}

	public void setClientWisePermissionAll(boolean clientWisePermissionAll) {
		this.clientWisePermissionAll = clientWisePermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
