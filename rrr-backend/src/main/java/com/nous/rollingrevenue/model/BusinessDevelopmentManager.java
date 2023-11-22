package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "business_development_manager")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BusinessDevelopmentManager extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_id")
	private Long bdmId;

	@Column(name = "bdm_name")
	private String bdmName;

	@Column(name = "bdm_display_name")
	private String bdmDisplayName;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "business_development_manager_to_business_unit", joinColumns = @JoinColumn(name = "bdm_id"), inverseJoinColumns = @JoinColumn(name = "bu_id"))
	private List<BusinessUnit> businessUnits = new ArrayList<>();

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "business_development_manager_to_region", joinColumns = @JoinColumn(name = "bdm_id"), inverseJoinColumns = @JoinColumn(name = "region_id"))
	private List<Region> regions = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

}
