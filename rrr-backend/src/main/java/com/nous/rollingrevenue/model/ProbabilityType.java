package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "probability_type")
public class ProbabilityType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "probability_type_id")
	private Long probabilityTypeId;
	
	@Column(name = "probability_type_name")
	private String probabilityTypeName;
	
	@Column(name = "percentage")
	private Integer percentage;

	public ProbabilityType() {

	}

	public ProbabilityType(Long probabilityTypeId, String probabilityTypeName, Integer percentage) {
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

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ProbabilityType [probabilityTypeId=" + probabilityTypeId + ", probabilityTypeName="
				+ probabilityTypeName + ", percentage=" + percentage + "]";
	}
	

}
