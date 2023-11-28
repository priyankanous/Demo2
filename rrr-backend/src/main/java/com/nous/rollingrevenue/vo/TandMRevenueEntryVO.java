package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TandMRevenueEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long tmRevenueEntryId;

	private AccountVO account;

	private OpportunityVO opportunity;

	@NotBlank(message = "Projectcode cannot be null or empty")
	private String projectCode;

	@NotNull(message = "projectStartDate cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	private BDMVO businessDevelopmentManager;

	private CurrencyVO currency;

	private ProbabilityTypeVO probabilityType;

	private RegionVO region;

	private WorkOrderVO workOrder;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate workOrderEndDate;

	private String workOrderStatus;

	private FinancialYearVO financialYear;

	@NotNull(message = "ResourceCount cannot be null or empty")
	@Range(min = 1, message = "ResourceCount can't be zero")
	private Integer resourceCount;

	@NotNull
	@Pattern(regexp = "^(T&M|FP)$", message = "allowed input: T&M or FP")
	private String pricingType;

	private String remarks;

	private String status;

	private List<RevenueResourceEntryVO> revenueResourceEntries = new ArrayList<>();

}
