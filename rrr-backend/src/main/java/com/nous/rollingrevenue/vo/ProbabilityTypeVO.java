package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProbabilityTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long probabilityTypeId;

	@NotBlank(message = "ProbabilityTypeName cannot be null or empty")
	private String probabilityTypeName;

	@NotNull(message = "percentage cannot be null or empty")
	@Min(value = 1, message = "Number should not be less than 1")
	@Max(value = 100, message = "Number should not be greater than 100")
	private Integer percentage;

	private boolean isActive;

	public ProbabilityTypeVO() {

	}

	public ProbabilityTypeVO(Long probabilityTypeId, String probabilityTypeName, Integer percentage, boolean isActive) {
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

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
