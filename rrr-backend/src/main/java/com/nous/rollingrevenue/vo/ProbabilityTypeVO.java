package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class ProbabilityTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long probabilityTypeId;

	@NotBlank(message = "ProbabilityTypeName cannot be null or empty")
	private String probabilityTypeName;

	@NotBlank(message = "Percentage cannot be null or empty")
	private String percentage;

	private boolean isActive;

	public ProbabilityTypeVO() {

	}

	public ProbabilityTypeVO(Long probabilityTypeId,
			@NotBlank(message = "ProbabilityTypeName cannot be null or empty") String probabilityTypeName,
			@NotBlank(message = "Percentage cannot be null or empty") String percentage, boolean isActive) {
		super();
		this.probabilityTypeId = probabilityTypeId;
		this.probabilityTypeName = probabilityTypeName;
		this.percentage = percentage;
		this.isActive = isActive;
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
