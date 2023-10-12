package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CocPracticePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cocPracticePermissionId;

	private boolean cocPracticePermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getCocPracticePermissionId() {
		return cocPracticePermissionId;
	}

	public void setCocPracticePermissionId(Long cocPracticePermissionId) {
		this.cocPracticePermissionId = cocPracticePermissionId;
	}

	public boolean isCocPracticePermissionAll() {
		return cocPracticePermissionAll;
	}

	public void setCocPracticePermissionAll(boolean cocPracticePermissionAll) {
		this.cocPracticePermissionAll = cocPracticePermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
