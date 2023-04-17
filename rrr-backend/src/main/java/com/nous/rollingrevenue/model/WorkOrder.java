package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "work_order")
@EntityListeners(AuditingEntityListener.class)
public class WorkOrder extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_order_id")
	private Long workOrderId;

	@Column(name = "work_order_number")
	private String workOrderNumber;

	@Column(name = "work_order_end_date")
	private LocalDate workOrderEndDate;

	@Column(name = "work_order_status")
	private String woStatus;
	
	@Column(name = "account_name")
	private String accountName;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public WorkOrder() {
		super();
	}
	
	

	public WorkOrder(Long workOrderId, String workOrderNumber, LocalDate workOrderEndDate, String woStatus,
			String accountName, boolean isActive) {
		super();
		this.workOrderId = workOrderId;
		this.workOrderNumber = workOrderNumber;
		this.workOrderEndDate = workOrderEndDate;
		this.woStatus = woStatus;
		this.accountName = accountName;
		this.isActive = isActive;
	}



	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public LocalDate getWorkOrderEndDate() {
		return workOrderEndDate;
	}

	public void setWorkOrderEndDate(LocalDate workOrderEndDate) {
		this.workOrderEndDate = workOrderEndDate;
	}

	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
