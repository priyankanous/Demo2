package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialYearPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long financialYearPermissionId;

	private boolean financialYearPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	private boolean isActive;

	public FinancialYearPermissionVO() {

	}

	public FinancialYearPermissionVO(Long financialYearPermissionId, boolean financialYearPermissionAll,
			CommonAdministrationPermissionVO commonAdministrationPermissionVO, boolean isActive) {
		super();
		this.financialYearPermissionId = financialYearPermissionId;
		this.financialYearPermissionAll = financialYearPermissionAll;
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
		this.isActive = isActive;
	}

	public Long getFinancialYearPermissionId() {
		return financialYearPermissionId;
	}

	public void setFinancialYearPermissionId(Long financialYearPermissionId) {
		this.financialYearPermissionId = financialYearPermissionId;
	}

	public boolean isFinancialYearPermissionAll() {
		return financialYearPermissionAll;
	}

	public void setFinancialYearPermissionAll(boolean financialYearPermissionAll) {
		this.financialYearPermissionAll = financialYearPermissionAll;
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
