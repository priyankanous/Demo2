package com.nous.rollingrevenue.model;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "revenue_resource_entry")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RevenueResourceEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_resource_entry_id")
	private Long revenueResourceEntryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit strategicBusinessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_head_id", referencedColumnName = "sbu_head_id")
	private StrategicBusinessUnitHead strategicBusinessUnitHead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
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
	@JsonManagedReference
	@JoinColumn(name = "business_type_id", referencedColumnName = "business_type_id")
	private BusinessType businessType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
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
	@JsonManagedReference
	@JoinColumn(name = "revenue_entry_id", referencedColumnName = "revenue_entry_id")
	private RevenueEntry revenueEntry;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "milestone_entry_id", referencedColumnName = "milestone_entry_id")
	private MilestoneEntry milestoneEntry;

}
