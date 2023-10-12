package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class PricingTypePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long pricingTypePermissionId;

	private boolean pricingTypePermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getPricingTypePermissionId() {
		return pricingTypePermissionId;
	}

	public void setPricingTypePermissionId(Long pricingTypePermissionId) {
		this.pricingTypePermissionId = pricingTypePermissionId;
	}

	public boolean isPricingTypePermissionAll() {
		return pricingTypePermissionAll;
	}

	public void setPricingTypePermissionAll(boolean pricingTypePermissionAll) {
		this.pricingTypePermissionAll = pricingTypePermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
