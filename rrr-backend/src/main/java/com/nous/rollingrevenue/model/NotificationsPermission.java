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
@Table(name = "notifications_permission")
@EntityListeners(AuditingEntityListener.class)
public class NotificationsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notifications_permission_id")
	private Long notificationsPermissionId;

	@Column(name = "is_assign_or_modify_cc_recipients")
	private Boolean isAssignOrModifyCcRecipientsRequired;

	@Column(name = "is_activate_or_deactivate")
	private Boolean isActivateOrDeactivateRequired;

	public NotificationsPermission() {

	}

	public NotificationsPermission(Long notificationsPermissionId, Boolean isAssignOrModifyCcRecipientsRequired,
			Boolean isActivateOrDeactivateRequired) {
		super();
		this.notificationsPermissionId = notificationsPermissionId;
		this.isAssignOrModifyCcRecipientsRequired = isAssignOrModifyCcRecipientsRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
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

}
