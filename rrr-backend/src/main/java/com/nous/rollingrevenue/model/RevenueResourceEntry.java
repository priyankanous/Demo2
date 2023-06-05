package com.nous.rollingrevenue.model;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenue_resource_entry")
@EntityListeners(AuditingEntityListener.class)
public class RevenueResourceEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_resource_entry_id")
	private Long revenueResourceEntryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit strategicBusinessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_head_id", referencedColumnName = "sbu_head_id")
	private StrategicBusinessUnitHead strategicBusinessUnitHead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "resource_start_date")
	private LocalDate resourceStartDate;

	@Column(name = "resource_end_date")
	private LocalDate resourceEndDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_type_id", referencedColumnName = "business_type_id")
	private BusinessType businessType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coc_practice_id", referencedColumnName = "coc_practice_id")
	private CocPractice cocPractice;

	@Column(name = "billing_rate_type")
	private String billingRateType;

	@Column(name = "billing_rate")
	private BigInteger billingRate;

	@Column(name = "leave_loss_factor")
	private Long leaveLossFactor;

	@Column(name = "revenue")
	private BigInteger revenue;

	@Column(name = "allocation")
	private Integer allocation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "revenue_entry_id", referencedColumnName = "revenue_entry_id")
	private RevenueEntry revenueEntry;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "milestone_entry_id", referencedColumnName = "milestone_entry_id")
	private MilestoneEntry milestoneEntry;

	public RevenueResourceEntry() {

	}

	public RevenueResourceEntry(Long revenueResourceEntryId, StrategicBusinessUnit strategicBusinessUnit,
			StrategicBusinessUnitHead strategicBusinessUnitHead, BusinessUnit businessUnit, Location location,
			String resourceName, String employeeId, LocalDate resourceStartDate, LocalDate resourceEndDate,
			BusinessType businessType, CocPractice cocPractice, String billingRateType, BigInteger billingRate,
			Long leaveLossFactor, BigInteger revenue, Integer allocation, RevenueEntry revenueEntry,
			MilestoneEntry milestoneEntry) {
		super();
		this.revenueResourceEntryId = revenueResourceEntryId;
		this.strategicBusinessUnit = strategicBusinessUnit;
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
		this.businessUnit = businessUnit;
		this.location = location;
		this.resourceName = resourceName;
		this.employeeId = employeeId;
		this.resourceStartDate = resourceStartDate;
		this.resourceEndDate = resourceEndDate;
		this.businessType = businessType;
		this.cocPractice = cocPractice;
		this.billingRateType = billingRateType;
		this.billingRate = billingRate;
		this.leaveLossFactor = leaveLossFactor;
		this.revenue = revenue;
		this.allocation = allocation;
		this.revenueEntry = revenueEntry;
		this.milestoneEntry = milestoneEntry;
	}

	public Long getRevenueResourceEntryId() {
		return revenueResourceEntryId;
	}

	public void setRevenueResourceEntryId(Long revenueResourceEntryId) {
		this.revenueResourceEntryId = revenueResourceEntryId;
	}

	public StrategicBusinessUnit getStrategicBusinessUnit() {
		return strategicBusinessUnit;
	}

	public void setStrategicBusinessUnit(StrategicBusinessUnit strategicBusinessUnit) {
		this.strategicBusinessUnit = strategicBusinessUnit;
	}

	public StrategicBusinessUnitHead getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(StrategicBusinessUnitHead strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getResourceStartDate() {
		return resourceStartDate;
	}

	public void setResourceStartDate(LocalDate resourceStartDate) {
		this.resourceStartDate = resourceStartDate;
	}

	public LocalDate getResourceEndDate() {
		return resourceEndDate;
	}

	public void setResourceEndDate(LocalDate resourceEndDate) {
		this.resourceEndDate = resourceEndDate;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public CocPractice getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPractice cocPractice) {
		this.cocPractice = cocPractice;
	}

	public String getBillingRateType() {
		return billingRateType;
	}

	public void setBillingRateType(String billingRateType) {
		this.billingRateType = billingRateType;
	}

	public BigInteger getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(BigInteger billingRate) {
		this.billingRate = billingRate;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public BigInteger getRevenue() {
		return revenue;
	}

	public void setRevenue(BigInteger revenue) {
		this.revenue = revenue;
	}

	public Integer getAllocation() {
		return allocation;
	}

	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}

	public RevenueEntry getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(RevenueEntry revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

	public MilestoneEntry getMilestoneEntry() {
		return milestoneEntry;
	}

	public void setMilestoneEntry(MilestoneEntry milestoneEntry) {
		this.milestoneEntry = milestoneEntry;
	}

}
