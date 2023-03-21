package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "work_order_status")
public class WorkOrderStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wo_status_id")
	private Long woStatusId;
	
	@Column(name = "wo_status_name")
	private String woStatusName;
	
	@Column(name = "wo_status_display_name")
	private String woStatusDisplayName;

	public WorkOrderStatus() {

	}

	public WorkOrderStatus(Long woStatusId, String woStatusName, String woStatusDisplayName) {
		this.woStatusId = woStatusId;
		this.woStatusName = woStatusName;
		this.woStatusDisplayName = woStatusDisplayName;
	}

	public Long getWoStatusId() {
		return woStatusId;
	}

	public void setWoStatusId(Long woStatusId) {
		this.woStatusId = woStatusId;
	}

	public String getWoStatusName() {
		return woStatusName;
	}

	public void setWoStatusName(String woStatusName) {
		this.woStatusName = woStatusName;
	}

	public String getWoStatusDisplayName() {
		return woStatusDisplayName;
	}

	public void setWoStatusDisplayName(String woStatusDisplayName) {
		this.woStatusDisplayName = woStatusDisplayName;
	}

	@Override
	public String toString() {
		return "WorkOrderStatus [woStatusId=" + woStatusId + ", woStatusName=" + woStatusName + ", woStatusDisplayName="
				+ woStatusDisplayName + "]";
	}
	

}
