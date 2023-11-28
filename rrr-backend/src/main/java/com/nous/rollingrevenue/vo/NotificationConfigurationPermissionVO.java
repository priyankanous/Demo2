package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
