package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long currencyPermissionId;

	private boolean currencyPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	private boolean isActive;

	public CurrencyPermissionVO() {

	}

	public CurrencyPermissionVO(Long currencyPermissionId, boolean currencyPermissionAll,
			CommonAdministrationPermissionVO commonAdministrationPermissionVO, boolean isActive) {
		super();
		this.currencyPermissionId = currencyPermissionId;
		this.currencyPermissionAll = currencyPermissionAll;
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
		this.isActive = isActive;
	}

	public Long getCurrencyPermissionId() {
		return currencyPermissionId;
	}

	public void setCurrencyPermissionId(Long currencyPermissionId) {
		this.currencyPermissionId = currencyPermissionId;
	}

	public boolean isCurrencyPermissionAll() {
		return currencyPermissionAll;
	}

	public void setCurrencyPermissionAll(boolean currencyPermissionAll) {
		this.currencyPermissionAll = currencyPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
