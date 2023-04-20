package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	@OneToMany(mappedBy = "workOrder")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	public WorkOrder() {
		super();
	}

	public WorkOrder(Long workOrderId, String workOrderNumber, LocalDate workOrderEndDate, String woStatus,
			Account account, boolean isActive, List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.workOrderId = workOrderId;
		this.workOrderNumber = workOrderNumber;
		this.workOrderEndDate = workOrderEndDate;
		this.woStatus = woStatus;
		this.account = account;
		this.isActive = isActive;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<RollingRevenueCommonEntry> getRollingRevenueCommonEntry() {
		return rollingRevenueCommonEntry;
	}

	public void setRollingRevenueCommonEntry(List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

}
