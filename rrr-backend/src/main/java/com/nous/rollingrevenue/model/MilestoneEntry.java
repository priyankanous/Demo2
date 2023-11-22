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
import lombok.Data;

@Entity
@Table(name = "milestone_entry")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
