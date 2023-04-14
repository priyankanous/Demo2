package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class CocPracticeVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long cocPracticeId;

	@NotBlank(message = "COCName cannot be null or empty")
	private String cocPracticeName;

	@NotBlank(message = "COCDisplayName cannot be null or empty")
	private String cocPracticeDisplayName;

	private BusinessUnitVO businessUnitVO;

	private boolean isActive;

	public CocPracticeVO() {

	}

	public CocPracticeVO(Long cocPracticeId, String cocPracticeName, String cocPracticeDisplayName,
			BusinessUnitVO businessUnitVO, boolean isActive) {
		super();
		this.cocPracticeId = cocPracticeId;
		this.cocPracticeName = cocPracticeName;
		this.cocPracticeDisplayName = cocPracticeDisplayName;
		this.businessUnitVO = businessUnitVO;
		this.isActive = isActive;
	}

	public Long getCocPracticeId() {
		return cocPracticeId;
	}

	public void setCocPracticeId(Long cocPracticeId) {
		this.cocPracticeId = cocPracticeId;
	}

	public String getCocPracticeName() {
		return cocPracticeName;
	}

	public void setCocPracticeName(String cocPracticeName) {
		this.cocPracticeName = cocPracticeName;
	}

	public String getCocPracticeDisplayName() {
		return cocPracticeDisplayName;
	}

	public void setCocPracticeDisplayName(String cocPracticeDisplayName) {
		this.cocPracticeDisplayName = cocPracticeDisplayName;
	}

	public BusinessUnitVO getBusinessUnitVO() {
		return businessUnitVO;
	}

	public void setBusinessUnitVO(BusinessUnitVO businessUnitVO) {
		this.businessUnitVO = businessUnitVO;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CocPracticeVO [cocPracticeId=" + cocPracticeId + ", cocPracticeName=" + cocPracticeName
				+ ", cocPracticeDisplayName=" + cocPracticeDisplayName + ", businessUnitVO=" + businessUnitVO
				+ ", isActive=" + isActive + "]";
	}


}