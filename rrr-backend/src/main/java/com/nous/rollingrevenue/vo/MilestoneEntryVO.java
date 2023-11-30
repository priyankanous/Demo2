package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MilestoneEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long milestoneEntryId;

	@NotBlank(message = "Projectcode cannot be null or empty")
	private String milestoneNumber;

	@NotNull(message = "MilestoneBillingDate cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate milestoneBillingDate;

	@NotNull(message = "MilestoneRevenue cannot be null or empty")
	private BigInteger milestoneRevenue;

	@NotNull(message = "MilestoneResourceCount cannot be null or empty")
	@Range(min = 1, message = "MilestoneResourceCount can't be zero")
	private Integer milestoneResourceCount;

	private List<RevenueResourceEntryVO> revenueResourceEntries = new ArrayList<>();

	public MilestoneEntryVO() {

	}

	public MilestoneEntryVO(Long milestoneEntryId, String milestoneNumber, LocalDate milestoneBillingDate,
			BigInteger milestoneRevenue, Integer milestoneResourceCount,
			List<RevenueResourceEntryVO> revenueResourceEntries) {
		super();
		this.milestoneEntryId = milestoneEntryId;
		this.milestoneNumber = milestoneNumber;
		this.milestoneBillingDate = milestoneBillingDate;
		this.milestoneRevenue = milestoneRevenue;
		this.milestoneResourceCount = milestoneResourceCount;
		this.revenueResourceEntries = revenueResourceEntries;
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

	public BigInteger getMilestoneRevenue() {
		return milestoneRevenue;
	}

	public void setMilestoneRevenue(BigInteger milestoneRevenue) {
		this.milestoneRevenue = milestoneRevenue;
	}

	public List<RevenueResourceEntryVO> getRevenueResourceEntries() {
		return revenueResourceEntries;
	}

	public void setRevenueResourceEntries(List<RevenueResourceEntryVO> revenueResourceEntries) {
		this.revenueResourceEntries = revenueResourceEntries;
	}

	public LocalDate getMilestoneBillingDate() {
		return milestoneBillingDate;
	}

	public void setMilestoneBillingDate(LocalDate milestoneBillingDate) {
		this.milestoneBillingDate = milestoneBillingDate;
	}

	public Integer getMilestoneResourceCount() {
		return milestoneResourceCount;
	}

	public void setMilestoneResourceCount(Integer milestoneResourceCount) {
		this.milestoneResourceCount = milestoneResourceCount;
	}

}
