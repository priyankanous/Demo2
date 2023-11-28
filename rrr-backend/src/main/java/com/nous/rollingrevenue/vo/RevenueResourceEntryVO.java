package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class RevenueResourceEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long revenueResourceEntryId;

	private StrategicBusinessUnitVO strategicBusinessUnit;

	private StrategicBusinessUnitHeadVO strategicBusinessUnitHead;

	private BusinessUnitVO businessUnit;

	private BusinessTypeVO businessType;

	private LocationVO location;

	private String resourceName;

	private String employeeId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate resourceStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate resourceEndDate;

	private CocPracticeVO cocPractice;

	private Long leaveLossFactor;

	private String billingRateType;

	private BigInteger billingRate;

	private BigInteger milestoneResourceRevenue;

	private Integer allocation;

}
