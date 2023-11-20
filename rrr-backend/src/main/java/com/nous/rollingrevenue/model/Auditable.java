package com.nous.rollingrevenue.model;

import static jakarta.persistence.TemporalType.TIMESTAMP;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable<U> {

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	@JsonIgnore
	protected U createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(name = "created_date", updatable = false)
	@JsonIgnore
	protected LocalDateTime createdDate;

	@LastModifiedBy
	@JsonIgnore
	protected U lastModifiedBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	@JsonIgnore
	protected LocalDateTime lastModifiedDate;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

}
