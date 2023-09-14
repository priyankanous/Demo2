package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class NotificationConfigurationPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long notificationConfigurationPermissionId;

	@NotNull(message = "isCreateNewNotificationEmailTemplateRequired cannot be null or empty")
	private Boolean isCreateNewNotificationEmailTemplateRequired;

	@NotNull(message = "isViewRequired cannot be null or empty")
	private Boolean isViewRequired;

	@NotNull(message = "isCopyRequired cannot be null or empty")
	private Boolean isCopyRequired;

	@NotNull(message = "isEditRequired cannot be null or empty")
	private Boolean isEditRequired;

	@NotNull(message = "isDeleteOrDeactivateRequired cannot be null or empty")
	private Boolean isDeleteOrDeactivateRequired;

	@NotNull(message = "isAssignRecipientsRequired cannot be null or empty")
	private Boolean isAssignRecipientsRequired;

	private boolean isActive;

	public NotificationConfigurationPermissionVO() {

	}

	public NotificationConfigurationPermissionVO(Long notificationConfigurationPermissionId,
			Boolean isCreateNewNotificationEmailTemplateRequired, Boolean isViewRequired, Boolean isCopyRequired,
			Boolean isEditRequired, Boolean isDeleteOrDeactivateRequired, Boolean isAssignRecipientsRequired,
			boolean isActive) {
		super();
		this.notificationConfigurationPermissionId = notificationConfigurationPermissionId;
		this.isCreateNewNotificationEmailTemplateRequired = isCreateNewNotificationEmailTemplateRequired;
		this.isViewRequired = isViewRequired;
		this.isCopyRequired = isCopyRequired;
		this.isEditRequired = isEditRequired;
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
		this.isAssignRecipientsRequired = isAssignRecipientsRequired;
		this.isActive = isActive;
	}

	public Long getNotificationConfigurationPermissionId() {
		return notificationConfigurationPermissionId;
	}

	public void setNotificationConfigurationPermissionId(Long notificationConfigurationPermissionId) {
		this.notificationConfigurationPermissionId = notificationConfigurationPermissionId;
	}

	public Boolean getIsCreateNewNotificationEmailTemplateRequired() {
		return isCreateNewNotificationEmailTemplateRequired;
	}

	public void setIsCreateNewNotificationEmailTemplateRequired(Boolean isCreateNewNotificationEmailTemplateRequired) {
		this.isCreateNewNotificationEmailTemplateRequired = isCreateNewNotificationEmailTemplateRequired;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsCopyRequired() {
		return isCopyRequired;
	}

	public void setIsCopyRequired(Boolean isCopyRequired) {
		this.isCopyRequired = isCopyRequired;
	}

	public Boolean getIsEditRequired() {
		return isEditRequired;
	}

	public void setIsEditRequired(Boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public Boolean getIsDeleteOrDeactivateRequired() {
		return isDeleteOrDeactivateRequired;
	}

	public void setIsDeleteOrDeactivateRequired(Boolean isDeleteOrDeactivateRequired) {
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
	}

	public Boolean getIsAssignRecipientsRequired() {
		return isAssignRecipientsRequired;
	}

	public void setIsAssignRecipientsRequired(Boolean isAssignRecipientsRequired) {
		this.isAssignRecipientsRequired = isAssignRecipientsRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
