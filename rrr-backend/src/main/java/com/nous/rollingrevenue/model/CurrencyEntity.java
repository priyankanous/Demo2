package com.nous.rollingrevenue.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "currency")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CurrencyEntity extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_id")
	private Long currencyId;

	@Column(name = "currency")
	private String currency;

	@Column(name = "currency_name")
	private String currencyName;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "conversion_rate")
	private BigDecimal conversionRate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@Column(name = "base_currency")
	private boolean baseCurrency;

	@OneToMany(mappedBy = "currency")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

}
