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

public class FPRevenueEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long fpRevenueEntryId;

	private AccountVO account;

	private OpportunityVO opportunity;

	@NotBlank(message = "Projectcode cannot be null or empty")
	private String projectCode;

	@NotNull
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

	@NotNull(message = "MilestoneCount cannot be null or empty")
	@Range(min = 1, message = "MilestoneCount can't be zero")
	private Integer milestoneCount;

	@NotNull
	@Pattern(regexp = "^(T&M|FP)$", message = "allowed input: T&M or FP")
	private String pricingType;

	private String remarks;

	private String status;

	private List<MilestoneEntryVO> milestones = new ArrayList<>();

	public FPRevenueEntryVO() {

	}

	public FPRevenueEntryVO(Long fpRevenueEntryId, AccountVO account, OpportunityVO opportunity, String projectCode,
			LocalDate projectStartDate, LocalDate projectEndDate, BDMVO businessDevelopmentManager, CurrencyVO currency,
			ProbabilityTypeVO probabilityType, RegionVO region, WorkOrderVO workOrder, LocalDate workOrderEndDate,
			String workOrderStatus, FinancialYearVO financialYear, Integer milestoneCount, String pricingType,
			String remarks, String status, List<MilestoneEntryVO> milestones) {
		super();
		this.fpRevenueEntryId = fpRevenueEntryId;
		this.account = account;
		this.opportunity = opportunity;
		this.projectCode = projectCode;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.businessDevelopmentManager = businessDevelopmentManager;
		this.currency = currency;
		this.probabilityType = probabilityType;
		this.region = region;
		this.workOrder = workOrder;
		this.workOrderEndDate = workOrderEndDate;
		this.workOrderStatus = workOrderStatus;
		this.financialYear = financialYear;
		this.milestoneCount = milestoneCount;
		this.pricingType = pricingType;
		this.remarks = remarks;
		this.status = status;
		this.milestones = milestones;
	}

	public Long getFpRevenueEntryId() {
		return fpRevenueEntryId;
	}

	public void setFpRevenueEntryId(Long fpRevenueEntryId) {
		this.fpRevenueEntryId = fpRevenueEntryId;
	}

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}

	public OpportunityVO getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(OpportunityVO opportunity) {
		this.opportunity = opportunity;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public LocalDate getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(LocalDate projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public LocalDate getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(LocalDate projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public BDMVO getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BDMVO businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public CurrencyVO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyVO currency) {
		this.currency = currency;
	}

	public ProbabilityTypeVO getProbabilityType() {
		return probabilityType;
	}

	public void setProbabilityType(ProbabilityTypeVO probabilityType) {
		this.probabilityType = probabilityType;
	}

	public RegionVO getRegion() {
		return region;
	}

	public void setRegion(RegionVO region) {
		this.region = region;
	}

	public WorkOrderVO getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderVO workOrder) {
		this.workOrder = workOrder;
	}

	public LocalDate getWorkOrderEndDate() {
		return workOrderEndDate;
	}

	public void setWorkOrderEndDate(LocalDate workOrderEndDate) {
		this.workOrderEndDate = workOrderEndDate;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getMilestoneCount() {
		return milestoneCount;
	}

	public void setMilestoneCount(Integer milestoneCount) {
		this.milestoneCount = milestoneCount;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MilestoneEntryVO> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<MilestoneEntryVO> milestones) {
		this.milestones = milestones;
	}

}
