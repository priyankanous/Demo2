package com.nous.rollingrevenue.model;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "probability_type")
@EntityListeners(AuditingEntityListener.class)
public class ProbabilityType extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "probability_type_id")
	private Long probabilityTypeId;

	@Column(name = "probability_type_name")
	private String probabilityTypeName;

	@Column(name = "percentage")
	private String percentage;

	@OneToMany(mappedBy = "probabilityType")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

	public ProbabilityType() {

	}

	public ProbabilityType(Long probabilityTypeId, String probabilityTypeName, String percentage) {
		super();
		this.probabilityTypeId = probabilityTypeId;
		this.probabilityTypeName = probabilityTypeName;
		this.percentage = percentage;
	}

	public Long getProbabilityTypeId() {
		return probabilityTypeId;
	}

	public void setProbabilityTypeId(Long probabilityTypeId) {
		this.probabilityTypeId = probabilityTypeId;
	}

	public String getProbabilityTypeName() {
		return probabilityTypeName;
	}

	public void setProbabilityTypeName(String probabilityTypeName) {
		this.probabilityTypeName = probabilityTypeName;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public List<RevenueEntry> getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(List<RevenueEntry> revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

}
