package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RevenueEntryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long revenueEntryId;

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

}
