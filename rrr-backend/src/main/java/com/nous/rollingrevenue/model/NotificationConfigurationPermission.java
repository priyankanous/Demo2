package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_configuration_permission")
@EntityListeners(AuditingEntityListener.class)
public class NotificationConfigurationPermission extends Auditable<String>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_configuration_permission_id")
	private Long notificationConfigurationPermissionId;
	
	@Column(name = "is_create_new_notification_email_template")
	private Boolean isCreateNewNotificationEmailTemplateRequired;

	@Column(name = "is_view")
	private Boolean isViewRequired;
	
	@Column(name = "is_copy")
	private Boolean isCopyRequired;
	
	@Column(name = "is_edit")
	private Boolean isEditRequired;
	
	@Column(name = "is_delete_or_deactivate")
	private Boolean isDeleteOrDeactivateRequired;
	
	@Column(name = "is_assign_recipients")
	private Boolean isAssignRecipientsRequired;

	public NotificationConfigurationPermission() {

	}

	public NotificationConfigurationPermission(Long notificationConfigurationPermissionId,
			Boolean isCreateNewNotificationEmailTemplateRequired, Boolean isViewRequired, Boolean isCopyRequired,
			Boolean isEditRequired, Boolean isDeleteOrDeactivateRequired, Boolean isAssignRecipientsRequired) {
		super();
		this.notificationConfigurationPermissionId = notificationConfigurationPermissionId;
		this.isCreateNewNotificationEmailTemplateRequired = isCreateNewNotificationEmailTemplateRequired;
		this.isViewRequired = isViewRequired;
		this.isCopyRequired = isCopyRequired;
		this.isEditRequired = isEditRequired;
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
		this.isAssignRecipientsRequired = isAssignRecipientsRequired;
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
	
}
