package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class WorkOrderVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long workOrderId;

	private String workOrderNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate workOrderEndDate;

	@NotBlank(message = "Status cannot be null or empty")
	private String workOrderStatus;

	private AccountVO account;

	private boolean isActive;

	public WorkOrderVO() {

	}

	public WorkOrderVO(Long workOrderId, String workOrderNumber, LocalDate workOrderEndDate, String workOrderStatus,
			AccountVO account, boolean isActive) {
		super();
		this.workOrderId = workOrderId;
		this.workOrderNumber = workOrderNumber;
		this.workOrderEndDate = workOrderEndDate;
		this.workOrderStatus = workOrderStatus;
		this.account = account;
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

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
