package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "strategic_business_unit_head")
@EntityListeners(AuditingEntityListener.class)
@Data
public class StrategicBusinessUnitHead extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_head_id")
	private Long sbuHeadId;

	@Column(name = "sbu_head_name")
	private String sbuHeadName;

	@Column(name = "sbu_head_display_name")
	private String sbuHeadDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit strategicbusinessUnit;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@OneToMany(mappedBy = "strategicBusinessUnitHead")
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "strategicBusinessUnitHead")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

}
