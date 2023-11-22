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
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Status extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Long statusId;

	@Column(name = "status_name")
	private String statusName;

	@Column(name = "status_display_name")
	private String statusDisplayName;

}
