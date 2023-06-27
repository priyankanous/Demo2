package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenue_entry")
@EntityListeners(AuditingEntityListener.class)
public class RevenueEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_entry_id")
	private Long revenueEntryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "opportunity_id", referencedColumnName = "opportunity_id")
	private Opportunity opportunity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager businessDevelopmentManager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
	private Currency currency;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "probability_type_id", referencedColumnName = "probability_type_id")
	private ProbabilityType probabilityType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "work_order_id", referencedColumnName = "work_order_id")
	private WorkOrder workOrder;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@Column(name = "resource_count")
	private Integer resourceCount;

	@Column(name = "milestone_count")
	private Integer milestoneCount;

	@Column(name = "pricingType")
	private String pricingType;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "revenueEntry")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

	@OneToMany(mappedBy = "revenueEntry")
	@JsonBackReference
	private List<MilestoneEntry> milestoneEntry = new ArrayList<>();

	public RevenueEntry() {

	}

	public RevenueEntry(Long revenueEntryId, Account account, Opportunity opportunity,
			BusinessDevelopmentManager businessDevelopmentManager, Currency currency, ProbabilityType probabilityType,
			Region region, WorkOrder workOrder, FinancialYear financialYear, Integer resourceCount,
			Integer milestoneCount, String pricingType, String remarks, String status) {
		super();
		this.revenueEntryId = revenueEntryId;
		this.account = account;
		this.opportunity = opportunity;
		this.businessDevelopmentManager = businessDevelopmentManager;
		this.currency = currency;
		this.probabilityType = probabilityType;
		this.region = region;
		this.workOrder = workOrder;
		this.financialYear = financialYear;
		this.resourceCount = resourceCount;
		this.milestoneCount = milestoneCount;
		this.pricingType = pricingType;
		this.remarks = remarks;
		this.status = status;
	}

	public Long getRevenueEntryId() {
		return revenueEntryId;
	}

	public void setRevenueEntryId(Long revenueEntryId) {
		this.revenueEntryId = revenueEntryId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Opportunity getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public BusinessDevelopmentManager getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BusinessDevelopmentManager businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public ProbabilityType getProbabilityType() {
		return probabilityType;
	}

	public void setProbabilityType(ProbabilityType probabilityType) {
		this.probabilityType = probabilityType;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	public Integer getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(Integer resourceCount) {
		this.resourceCount = resourceCount;
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

	public List<RevenueResourceEntry> getRevenueResourceEntry() {
		return revenueResourceEntry;
	}

	public void setRevenueResourceEntry(List<RevenueResourceEntry> revenueResourceEntry) {
		this.revenueResourceEntry = revenueResourceEntry;
	}

	public List<MilestoneEntry> getMilestoneEntry() {
		return milestoneEntry;
	}

	public void setMilestoneEntry(List<MilestoneEntry> milestoneEntry) {
		this.milestoneEntry = milestoneEntry;
	}

}
