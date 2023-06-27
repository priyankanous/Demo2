package com.nous.rollingrevenue.model;

import java.math.BigInteger;
import java.time.LocalDate;
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
@Table(name = "milestone_entry")
@EntityListeners(AuditingEntityListener.class)
public class MilestoneEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_entry_id")
	private Long milestoneEntryId;

	@Column(name = "milestone_number")
	private String milestoneNumber;

	@Column(name = "milestone_billing_date")
	private LocalDate milestoneBillingDate;

	@Column(name = "milestone_revenue")
	private BigInteger milestoneRevenue;

	@Column(name = "milestone_resource_count")
	private Integer milestoneResourceCount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "revenue_entry_id", referencedColumnName = "revenue_entry_id")
	private RevenueEntry revenueEntry;

	@OneToMany(mappedBy = "milestoneEntry")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

	public MilestoneEntry() {

	}

	public MilestoneEntry(Long milestoneEntryId, String milestoneNumber, LocalDate milestoneBillingDate,
			BigInteger milestoneRevenue, Integer milestoneResourceCount, RevenueEntry revenueEntry) {
		super();
		this.milestoneEntryId = milestoneEntryId;
		this.milestoneNumber = milestoneNumber;
		this.milestoneBillingDate = milestoneBillingDate;
		this.milestoneRevenue = milestoneRevenue;
		this.milestoneResourceCount = milestoneResourceCount;
		this.revenueEntry = revenueEntry;
	}

	public Long getMilestoneEntryId() {
		return milestoneEntryId;
	}

	public void setMilestoneEntryId(Long milestoneEntryId) {
		this.milestoneEntryId = milestoneEntryId;
	}

	public String getMilestoneNumber() {
		return milestoneNumber;
	}

	public void setMilestoneNumber(String milestoneNumber) {
		this.milestoneNumber = milestoneNumber;
	}

	public LocalDate getMilestoneBillingDate() {
		return milestoneBillingDate;
	}

	public void setMilestoneBillingDate(LocalDate milestoneBillingDate) {
		this.milestoneBillingDate = milestoneBillingDate;
	}

	public BigInteger getMilestoneRevenue() {
		return milestoneRevenue;
	}

	public void setMilestoneRevenue(BigInteger milestoneRevenue) {
		this.milestoneRevenue = milestoneRevenue;
	}

	public Integer getMilestoneResourceCount() {
		return milestoneResourceCount;
	}

	public void setMilestoneResourceCount(Integer milestoneResourceCount) {
		this.milestoneResourceCount = milestoneResourceCount;
	}

	public RevenueEntry getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(RevenueEntry revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

	public List<RevenueResourceEntry> getRevenueResourceEntry() {
		return revenueResourceEntry;
	}

	public void setRevenueResourceEntry(List<RevenueResourceEntry> revenueResourceEntry) {
		this.revenueResourceEntry = revenueResourceEntry;
	}

}
