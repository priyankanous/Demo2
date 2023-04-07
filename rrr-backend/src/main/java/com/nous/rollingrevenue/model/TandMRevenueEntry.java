package com.nous.rollingrevenue.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TM_revenue_entry")
public class TandMRevenueEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long tmRevenueId;

	@Column(name = "leave_loss_factor")
	private Long leaveLossFactor;

	@Column(name = "billing_rate_type")
	private String billingRateType;

	@Column(name = "billing_rate")
	private Long billingRate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "common_entry_id")
	private RollingRevenueCommonEntry commonEntry;

	@OneToMany(mappedBy = "tmRevenueEntry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ResourcesEntry> resourcesEntries;

	public Long getTmRevenueId() {
		return tmRevenueId;
	}

	public void setTmRevenueId(Long tmRevenueId) {
		this.tmRevenueId = tmRevenueId;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public String getBillingRateType() {
		return billingRateType;
	}

	public void setBillingRateType(String billingRateType) {
		this.billingRateType = billingRateType;
	}

	public Long getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Long billingRate) {
		this.billingRate = billingRate;
	}

	public RollingRevenueCommonEntry getCommonEntry() {
		return commonEntry;
	}

	public void setCommonEntry(RollingRevenueCommonEntry commonEntry) {
		this.commonEntry = commonEntry;
	}

	public Set<ResourcesEntry> getResourcesEntries() {
		return resourcesEntries;
	}

	public void setResourcesEntries(Set<ResourcesEntry> resourcesEntries) {
		this.resourcesEntries = resourcesEntries;
	}

}
