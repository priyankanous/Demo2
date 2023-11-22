package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "business_unit")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BusinessUnit extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bu_id")
	private Long businessUnitId;

	@Column(name = "bu_name")
	private String businessUnitName;

	@Column(name = "bu_display_name")
	private String businessUnitDisplayName;

	@ManyToMany(mappedBy = "businessUnits")
	@JsonManagedReference
	private List<BusinessDevelopmentManager> businessDevlopmentManagers = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<StrategicBusinessUnit> strategicBusinessUnits = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<CocPractice> cocPractices = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

}
