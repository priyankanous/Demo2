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
import lombok.Data;

@Entity
@Table(name = "revenue_entry")
@EntityListeners(AuditingEntityListener.class)
@Data
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
	private CurrencyEntity currency;

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

}
