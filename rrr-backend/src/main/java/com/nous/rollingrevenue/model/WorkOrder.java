package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import lombok.Data;

@Entity
@Table(name = "work_order")
@EntityListeners(AuditingEntityListener.class)
@Data
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

	@OneToMany(mappedBy = "workOrder")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

}
