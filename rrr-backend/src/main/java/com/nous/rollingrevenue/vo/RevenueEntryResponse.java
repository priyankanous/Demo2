package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RevenueEntryResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Set<RevenueEntryVO> revenueEntries = new HashSet<>();

	private FinancialYearRevenue financialYearRevenue;

	private String financialYearName;

	public RevenueEntryResponse() {

	}

	public RevenueEntryResponse(Set<RevenueEntryVO> revenueEntries, FinancialYearRevenue financialYearRevenue,
			String financialYearName) {
		super();
		this.revenueEntries = revenueEntries;
		this.financialYearRevenue = financialYearRevenue;
		this.financialYearName = financialYearName;
	}

	public Set<RevenueEntryVO> getRevenueEntries() {
		return revenueEntries;
	}

	public void setRevenueEntries(Set<RevenueEntryVO> revenueEntries) {
		this.revenueEntries = revenueEntries;
	}

	public FinancialYearRevenue getFinancialYearRevenue() {
		return financialYearRevenue;
	}

	public void setFinancialYearRevenue(FinancialYearRevenue financialYearRevenue) {
		this.financialYearRevenue = financialYearRevenue;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

}
