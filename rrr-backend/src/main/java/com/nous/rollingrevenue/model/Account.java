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
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_name")
	private String accountName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region regions;

	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<Opportunity> opportunities = new ArrayList<>();

	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<WorkOrder> workOrders = new ArrayList<>();

	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "account")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

	public Account() {

	}

	public Account(Long accountId, String accountName, Region regions, List<Opportunity> opportunities,
			List<WorkOrder> workOrders, List<AnnualTargetEntry> annualTargetEntries, List<RevenueEntry> revenueEntry) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.regions = regions;
		this.opportunities = opportunities;
		this.workOrders = workOrders;
		this.annualTargetEntries = annualTargetEntries;
		this.revenueEntry = revenueEntry;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Region getRegions() {
		return regions;
	}

	public void setRegions(Region regions) {
		this.regions = regions;
	}

	public List<Opportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

	public List<WorkOrder> getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(List<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

	public List<RevenueEntry> getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(List<RevenueEntry> revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

}
