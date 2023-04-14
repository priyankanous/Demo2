package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class GlobalMonthlyLeaveLossFactorVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long leaveLossFactorId;

	@NotEmpty(message = "month must not be empty")
	private String month;

	@NotNull(message = "off shore must not be empty")
	private Long offShore;

	@NotNull(message = "on site name must not be empty")
	private Long onSite;

	private FinancialYearVO financialYearVO;

	private boolean isActive;

	public GlobalMonthlyLeaveLossFactorVO() {

	}

	public GlobalMonthlyLeaveLossFactorVO(Long leaveLossFactorId, String month, Long offShore, Long onSite,
			FinancialYearVO financialYearVO, boolean isActive) {
		super();
		this.leaveLossFactorId = leaveLossFactorId;
		this.month = month;
		this.offShore = offShore;
		this.onSite = onSite;
		this.financialYearVO = financialYearVO;
		this.isActive = isActive;
	}

	public Long getLeaveLossFactorId() {
		return leaveLossFactorId;
	}

	public void setLeaveLossFactorId(Long leaveLossFactorId) {
		this.leaveLossFactorId = leaveLossFactorId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getOffShore() {
		return offShore;
	}

	public void setOffShore(Long offShore) {
		this.offShore = offShore;
	}

	public Long getOnSite() {
		return onSite;
	}

	public void setOnSite(Long onSite) {
		this.onSite = onSite;
	}

	public FinancialYearVO getFinancialYearVO() {
		return financialYearVO;
	}

	public void setFinancialYearVO(FinancialYearVO financialYearVO) {
		this.financialYearVO = financialYearVO;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "GlobalMonthlyLeaveLossFactorVO [leaveLossFactorId=" + leaveLossFactorId + ", month=" + month
				+ ", offShore=" + offShore + ", onSite=" + onSite + ", financialYearVO=" + financialYearVO + ", isActive="
				+ isActive + "]";
	}
	

}
