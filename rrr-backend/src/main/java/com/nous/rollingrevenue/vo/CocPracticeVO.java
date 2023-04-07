package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class CocPracticeVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long cocPracticeId;

	@NotBlank(message = "COCName cannot be null or empty")
	private String cocPracticeName;

	@NotBlank(message = "COCDisplayName cannot be null or empty")
	private String cocPracticeDisplayName;

	@NotBlank(message = "BUDisplayName cannot be null or empty")
	private String buDisplayName;

	private boolean isActive;

	public CocPracticeVO() {

	}

	public CocPracticeVO(Long cocPracticeId, String cocPracticeName, String cocPracticeDisplayName,
			String buDisplayName, boolean isActive) {
		super();
		this.cocPracticeId = cocPracticeId;
		this.cocPracticeName = cocPracticeName;
		this.cocPracticeDisplayName = cocPracticeDisplayName;
		this.buDisplayName = buDisplayName;
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

	public String getBuDisplayName() {
		return buDisplayName;
	}

	public void setBuDisplayName(String buDisplayName) {
		this.buDisplayName = buDisplayName;
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
				+ ", cocPracticeDisplayName=" + cocPracticeDisplayName + ", buDisplayName=" + buDisplayName
				+ ", isActive=" + isActive + "]";
	}
	

}