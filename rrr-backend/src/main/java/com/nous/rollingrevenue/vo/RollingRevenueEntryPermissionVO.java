package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RollingRevenueEntryPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rollingrevenueEntryPermissionId;

	private boolean rollingrevenueEntryPermissionAll;

	private boolean viewAllEntriesRequired;

	private boolean addRevenueEntryRequired;

	private boolean editRevenueEntryRequired;

	private boolean deleteRevenueEntryRequired;

	private boolean copyRevenueEntryRequired;

	private boolean submitRevenueEntryRequired;

	private boolean exportRequired;

	private boolean isActive;

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
