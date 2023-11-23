package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AnnualTargetEntryVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long annualTargetEntryId;

	private FinancialYearVO financialYear;

	private BusinessUnitVO businessUnit;

	private StrategicBusinessUnitVO startegicBusinessUnit;

	private StrategicBusinessUnitHeadVO strategicBusinessUnitHead;

	private LocationVO location;

	private RegionVO region;

	private AccountVO account;

	private BusinessTypeVO businessType;

	private CocPracticeVO cocPractice;

	private BDMVO businessDevelopmentManager;

	private BigInteger q1FYB;

	private BigInteger q1FYS;

	private BigInteger q1FYT;

	private BigInteger q2FYB;

	private BigInteger q2FYS;

	private BigInteger q2FYT;

	private BigInteger q3FYB;

	private BigInteger q3FYS;

	private BigInteger q3FYT;

	private BigInteger q4FYB;

	private BigInteger q4FYS;

	private BigInteger q4FYT;

	private BigInteger fY;

	private boolean isActive;

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
