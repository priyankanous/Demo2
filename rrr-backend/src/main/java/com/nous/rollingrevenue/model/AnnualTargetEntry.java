package com.nous.rollingrevenue.model;

import java.math.BigInteger;

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
@Table(name = "annual_target_entry")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AnnualTargetEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "annual_target_entry_id")
	private Long annualTargetEntryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit startegicBusinessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_head_id", referencedColumnName = "sbu_head_id")
	private StrategicBusinessUnitHead strategicBusinessUnitHead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_type_id", referencedColumnName = "business_type_id")
	private BusinessType businessType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "coc_practice_id", referencedColumnName = "coc_practice_id")
	private CocPractice cocPractice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager businessDevelopmentManager;

	@Column(name = "q1fy_s")
	private BigInteger q1FYS;

	@Column(name = "q1fy_b")
	private BigInteger q1FYB;

	@Column(name = "q1fy_t")
	private BigInteger q1FYT;

	@Column(name = "q2fy_s")
	private BigInteger q2FYS;

	@Column(name = "q2fy_b")
	private BigInteger q2FYB;

	@Column(name = "q2fy_t")
	private BigInteger q2FYT;

	@Column(name = "q3fy_s")
	private BigInteger q3FYS;

	@Column(name = "q3fy_b")
	private BigInteger q3FYB;

	@Column(name = "q3fy_t")
	private BigInteger q3FYT;

	@Column(name = "q4fy_s")
	private BigInteger q4FYS;

	@Column(name = "q4fy_b")
	private BigInteger q4FYB;

	@Column(name = "q4fy_t")
	private BigInteger q4FYT;

	@Column(name = "fy")
	private BigInteger fY;

	public AnnualTargetEntry() {

	}

}
