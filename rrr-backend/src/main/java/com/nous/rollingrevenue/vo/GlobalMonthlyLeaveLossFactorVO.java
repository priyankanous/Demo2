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

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long leaveLossFactorId;

	@NotEmpty(message = "month must not be empty")
	private String month;

	@NotNull(message = "off shore must not be empty")
	private Long offShore;

	@NotNull(message = "on site name must not be empty")
	private Long onSite;

	@NotEmpty(message = "financial year must not be empty")
	private String financialYear;

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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

}
