package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class RegionReportInDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "FinancialYearName cannot be null or empty")
	private String financialYearName;

	private Long businessUnitId;

	private Long sbuId;

	private Long sbuHeadId;
	
	private Long businessTypeId;
	
	private Long probabilityTypeId;

	private Long locationId;

	private Long accountId;

	private Long bdmId;

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public Long getSbuId() {
		return sbuId;
	}

	public void setSbuId(Long sbuId) {
		this.sbuId = sbuId;
	}

	public Long getSbuHeadId() {
		return sbuHeadId;
	}

	public void setSbuHeadId(Long sbuHeadId) {
		this.sbuHeadId = sbuHeadId;
	}

	public Long getProbabilityTypeId() {
		return probabilityTypeId;
	}

	public void setProbabilityTypeId(Long probabilityTypeId) {
		this.probabilityTypeId = probabilityTypeId;
	}
	
	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getBdmId() {
		return bdmId;
	}

	public void setBdmId(Long bdmId) {
		this.bdmId = bdmId;
	}
	

}
