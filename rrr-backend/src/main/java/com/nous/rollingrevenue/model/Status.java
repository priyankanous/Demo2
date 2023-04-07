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
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
public class Status extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "status_display_name")
	private String statusDisplayName;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public Status() {

	}

	public Status(Long statusId, String statusName, String statusDisplayName, boolean isActive) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusDisplayName = statusDisplayName;
		this.isActive = isActive;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDisplayName() {
		return statusDisplayName;
	}

	public void setStatusDisplayName(String statusDisplayName) {
		this.statusDisplayName = statusDisplayName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + ", statusDisplayName="
				+ statusDisplayName + ", isActive=" + isActive + "]";
	}

}
