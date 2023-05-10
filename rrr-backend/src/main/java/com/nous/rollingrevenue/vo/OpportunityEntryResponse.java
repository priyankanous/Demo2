package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OpportunityEntryResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Set<OpportunityEntryVO> opportunities = new HashSet<>();

	private FinancialYearRevenue financialYearRevenue;

	private String financialYearName;

	public OpportunityEntryResponse() {

	}

	public OpportunityEntryResponse(Set<OpportunityEntryVO> opportunities, FinancialYearRevenue financialYearRevenue,
			String financialYearName) {
		super();
		this.opportunities = opportunities;
		this.financialYearRevenue = financialYearRevenue;
		this.financialYearName = financialYearName;
	}

	public Set<OpportunityEntryVO> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(Set<OpportunityEntryVO> opportunities) {
		this.opportunities = opportunities;
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
