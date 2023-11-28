package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class OpportunityRevenueRequest implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String businessUnit;

	private String strategicBusinessUnit;

	private String strategicBusinessUnitHead;

	private String businessDevelopmentManager;

	private String businessType;

	private String account;

	private String region;

	private String location;

	private String probabilityType;

	private String cocPractice;

	private String status;

	private String financialYearName;

}
