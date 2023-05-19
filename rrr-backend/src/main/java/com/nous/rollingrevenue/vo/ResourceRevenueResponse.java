package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ResourceRevenueResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private FinancialYearRevenue financialYearRevenue;

	private FinancialYearTMRevenue financialYearTMRevenue;

	private String financialYearName;

	public FinancialYearRevenue getFinancialYearRevenue() {
		return financialYearRevenue;
	}

	public void setFinancialYearRevenue(FinancialYearRevenue financialYearRevenue) {
		this.financialYearRevenue = financialYearRevenue;
	}

	public FinancialYearTMRevenue getFinancialYearTMRevenue() {
		return financialYearTMRevenue;
	}

	public void setFinancialYearTMRevenue(FinancialYearTMRevenue financialYearTMRevenue) {
		this.financialYearTMRevenue = financialYearTMRevenue;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

}
