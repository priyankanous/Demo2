package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class SBUPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sbuPermissionId;

	private boolean sbuPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getSbuPermissionId() {
		return sbuPermissionId;
	}

	public void setSbuPermissionId(Long sbuPermissionId) {
		this.sbuPermissionId = sbuPermissionId;
	}

	public boolean isSbuPermissionAll() {
		return sbuPermissionAll;
	}

	public void setSbuPermissionAll(boolean sbuPermissionAll) {
		this.sbuPermissionAll = sbuPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
