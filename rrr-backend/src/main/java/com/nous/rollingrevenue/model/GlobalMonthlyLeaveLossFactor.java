package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "global_monthly_leave_loss_factor")
@EntityListeners(AuditingEntityListener.class)
@Data
public class GlobalMonthlyLeaveLossFactor extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leave_loss_factor_id")
	private Long leaveLossFactorId;

	@Column(name = "month")
	private String month;

	@Column(name = "off_shore")
	private Long offShore;

	@Column(name = "on_site")
	private Long onSite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

}
