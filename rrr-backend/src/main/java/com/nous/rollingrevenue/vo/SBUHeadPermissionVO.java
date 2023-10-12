package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class SBUHeadPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sbuHeadPermissionId;

	private boolean sbuHeadPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getSbuHeadPermissionId() {
		return sbuHeadPermissionId;
	}

	public void setSbuHeadPermissionId(Long sbuHeadPermissionId) {
		this.sbuHeadPermissionId = sbuHeadPermissionId;
	}

	public boolean isSbuHeadPermissionAll() {
		return sbuHeadPermissionAll;
	}

	public void setSbuHeadPermissionAll(boolean sbuHeadPermissionAll) {
		this.sbuHeadPermissionAll = sbuHeadPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
