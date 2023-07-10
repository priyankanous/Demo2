package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class SBUClientTypeReportInDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "FinancialYearName cannot be null or empty")
	private String financialYearName;

	private Long regionId;

	private Long businessUnitId;

	private Long sbuHeadId;

	private Long BussinessTypeId;

	private Long ProbabilityTypeId;

	private Long locationId;

	private Long bdmId;

	public Long getBussinessTypeId() {
		return BussinessTypeId;
	}

	public void setBussinessTypeId(Long bussinessTypeId) {
		BussinessTypeId = bussinessTypeId;
	}

	public Long getProbabilityTypeId() {
		return ProbabilityTypeId;
	}

	public void setProbabilityTypeId(Long probabilityTypeId) {
		ProbabilityTypeId = probabilityTypeId;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public Long getSbuHeadId() {
		return sbuHeadId;
	}

	public void setSbuHeadId(Long sbuHeadId) {
		this.sbuHeadId = sbuHeadId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getBdmId() {
		return bdmId;
	}

	public void setBdmId(Long bdmId) {
		this.bdmId = bdmId;
	}

}
