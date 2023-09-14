package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class NotificationsPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long notificationsPermissionId;

	@NotNull(message = "isAssignOrModifyCcRecipientsRequired cannot be null or empty")
	private Boolean isAssignOrModifyCcRecipientsRequired;

	@NotNull(message = "isActivateOrDeactivateRequired cannot be null or empty")
	private Boolean isActivateOrDeactivateRequired;

	private boolean isActive;

	public NotificationsPermissionVO() {

	}

	public NotificationsPermissionVO(Long notificationsPermissionId, Boolean isAssignOrModifyCcRecipientsRequired,
			Boolean isActivateOrDeactivateRequired, boolean isActive) {
		super();
		this.notificationsPermissionId = notificationsPermissionId;
		this.isAssignOrModifyCcRecipientsRequired = isAssignOrModifyCcRecipientsRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
		this.isActive = isActive;
	}

	public Long getNotificationsPermissionId() {
		return notificationsPermissionId;
	}

	public void setNotificationsPermissionId(Long notificationsPermissionId) {
		this.notificationsPermissionId = notificationsPermissionId;
	}

	public Boolean getIsAssignOrModifyCcRecipientsRequired() {
		return isAssignOrModifyCcRecipientsRequired;
	}

	public void setIsAssignOrModifyCcRecipientsRequired(Boolean isAssignOrModifyCcRecipientsRequired) {
		this.isAssignOrModifyCcRecipientsRequired = isAssignOrModifyCcRecipientsRequired;
	}

	public Boolean getIsActivateOrDeactivateRequired() {
		return isActivateOrDeactivateRequired;
	}

	public void setIsActivateOrDeactivateRequired(Boolean isActivateOrDeactivateRequired) {
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
