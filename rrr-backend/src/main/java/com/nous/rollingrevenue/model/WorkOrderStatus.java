package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "work_order_status")
@EntityListeners(AuditingEntityListener.class)
@Data
public class WorkOrderStatus extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wo_status_id")
	private Long woStatusId;

	@Column(name = "wo_status_name")
	private String woStatusName;

	@Column(name = "wo_status_display_name")
	private String woStatusDisplayName;

}
