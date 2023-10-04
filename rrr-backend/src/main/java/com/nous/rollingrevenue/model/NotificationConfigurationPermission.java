package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_configuration_permission")
@EntityListeners(AuditingEntityListener.class)
public class NotificationConfigurationPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notification_configuration_permission_id")
	private Long notificationConfigurationPermissionId;

	@Column(name = "notification_configuration_permission_all")
	private Boolean isNotificationConfigurationPermissionAll;

	@OneToMany(mappedBy = "notificationConfigurationPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public NotificationConfigurationPermission() {

	}

	public NotificationConfigurationPermission(Long notificationConfigurationPermissionId,
			Boolean isNotificationConfigurationPermissionAll, List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.notificationConfigurationPermissionId = notificationConfigurationPermissionId;
		this.isNotificationConfigurationPermissionAll = isNotificationConfigurationPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getNotificationConfigurationPermissionId() {
		return notificationConfigurationPermissionId;
	}

	public void setNotificationConfigurationPermissionId(Long notificationConfigurationPermissionId) {
		this.notificationConfigurationPermissionId = notificationConfigurationPermissionId;
	}

	public Boolean getIsNotificationConfigurationPermissionAll() {
		return isNotificationConfigurationPermissionAll;
	}

	public void setIsNotificationConfigurationPermissionAll(Boolean isNotificationConfigurationPermissionAll) {
		this.isNotificationConfigurationPermissionAll = isNotificationConfigurationPermissionAll;
	}

	public List<AdministrationPermission> getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(List<AdministrationPermission> administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

	public AdministrationCommonPermission getAdministrationCommonPermission() {
		return administrationCommonPermission;
	}

	public void setAdministrationCommonPermission(AdministrationCommonPermission administrationCommonPermission) {
		this.administrationCommonPermission = administrationCommonPermission;
	}

}
