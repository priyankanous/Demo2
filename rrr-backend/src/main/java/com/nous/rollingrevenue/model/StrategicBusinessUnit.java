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
@Table(name = "strategic_business_unit")
@EntityListeners(AuditingEntityListener.class)
@Data
public class StrategicBusinessUnit extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_id")
	private Long sbuId;

	@Column(name = "sbu_name")
	private String sbuName;

	@Column(name = "sbu_display_name")
	private String sbuDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@OneToMany(mappedBy = "strategicbusinessUnit")
	@JsonBackReference
	private List<StrategicBusinessUnitHead> strategicBusinessUnitHeads = new ArrayList<>();

	@OneToMany(mappedBy = "startegicBusinessUnit")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "strategicBusinessUnit")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

}
