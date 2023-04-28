package com.nous.rollingrevenue.model;

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
@Table(name = "resources_entry")
@EntityListeners(AuditingEntityListener.class)
public class ResourcesEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resource_id")
	private Long resourceId;

	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "resource_start_date")
	private LocalDate resourceStartDate;

	@Column(name = "resource_end_date")
	private LocalDate resourceEndDate;

	@Column(name = "leave_loss_factor")
	private Long leaveLossFactor;

	@Column(name = "billing_rate")
	private Long billingRate;

	@Column(name = "allocation")
	private Long allocation;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tm_revenue_id")
	private TandMRevenueEntry tmRevenueEntry;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_id")
	private Opportunity opportunity;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
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

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public Long getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Long billingRate) {
		this.billingRate = billingRate;
	}

	public TandMRevenueEntry getTmRevenueEntry() {
		return tmRevenueEntry;
	}

	public void setTmRevenueEntry(TandMRevenueEntry tmRevenueEntry) {
		this.tmRevenueEntry = tmRevenueEntry;
	}

	public Opportunity getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public Long getAllocation() {
		return allocation;
	}

	public void setAllocation(Long allocation) {
		this.allocation = allocation;
	}

}
