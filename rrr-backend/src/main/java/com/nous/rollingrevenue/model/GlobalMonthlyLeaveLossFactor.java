package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "global_monthly_leave_loss_factor")
public class GlobalMonthlyLeaveLossFactor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_loss_factor_id")
	private Long leaveLossFactorId;

	@Column(name = "month")
	private String month;

	@Column(name = "off_shore")
	private Long offShore;

	@Column(name = "on_site")
	private Long onSite;

	@Column(name = "financial_year")
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

	public GlobalMonthlyLeaveLossFactor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GlobalMonthlyLeaveLossFactor(Long leaveLossFactorId, String month, Long offShore, Long onSite,
			String financialYear) {
		super();
		this.leaveLossFactorId = leaveLossFactorId;
		this.month = month;
		this.offShore = offShore;
		this.onSite = onSite;
		this.financialYear = financialYear;
	}

}
