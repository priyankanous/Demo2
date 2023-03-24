package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProbabilityTypeVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long probabilityTypeId;
	
	@NotBlank(message = "ProbabilityTypeName cannot be null or empty")
	private String probabilityTypeName;
	
	@Min(value = 1, message = "Number should not be less than 1")
    @Max(value = 100, message = "Number should not be greater than 100")
	private Integer percentage;

	public ProbabilityTypeVO() {

	}

	public ProbabilityTypeVO(Long probabilityTypeId, String probabilityTypeName, Integer percentage) {
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
		return "ProbabilityTypeVO [probabilityTypeId=" + probabilityTypeId + ", probabilityTypeName="
				+ probabilityTypeName + ", percentage=" + percentage + "]";
	}
	

}
