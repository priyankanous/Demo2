package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResourceEntryResponse implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private List<TandMResourceEntryVO> tmResourceEntries = new ArrayList<>();

	private List<FPResourceEntryVO> fpResourceEntries = new ArrayList<>();

	private FinancialYearRevenue financialYearRevenue;

	private FinancialYearTMRevenue financialYearTMRevenue;

	private String financialYearName;

	public ResourceEntryResponse() {

	}

	public ResourceEntryResponse(List<TandMResourceEntryVO> tmResourceEntries,
			List<FPResourceEntryVO> fpResourceEntries, FinancialYearRevenue financialYearRevenue,
			String financialYearName) {
		super();
		this.tmResourceEntries = tmResourceEntries;
		this.fpResourceEntries = fpResourceEntries;
		this.financialYearRevenue = financialYearRevenue;
		this.financialYearName = financialYearName;
	}

	public List<TandMResourceEntryVO> getTmResourceEntries() {
		return tmResourceEntries;
	}

	public void setTmResourceEntries(List<TandMResourceEntryVO> tmResourceEntries) {
		this.tmResourceEntries = tmResourceEntries;
	}

	public List<FPResourceEntryVO> getFpResourceEntries() {
		return fpResourceEntries;
	}

	public void setFpResourceEntries(List<FPResourceEntryVO> fpResourceEntries) {
		this.fpResourceEntries = fpResourceEntries;
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

	public FinancialYearTMRevenue getFinancialYearTMRevenue() {
		return financialYearTMRevenue;
	}

	public void setFinancialYearTMRevenue(FinancialYearTMRevenue financialYearTMRevenue) {
		this.financialYearTMRevenue = financialYearTMRevenue;
	}

}
