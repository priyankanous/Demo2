package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class AccountPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long accountPermissionId;

	private boolean accountPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getAccountPermissionId() {
		return accountPermissionId;
	}

	public void setAccountPermissionId(Long accountPermissionId) {
		this.accountPermissionId = accountPermissionId;
	}

	public boolean isAccountPermissionAll() {
		return accountPermissionAll;
	}

	public void setAccountPermissionAll(boolean accountPermissionAll) {
		this.accountPermissionAll = accountPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
