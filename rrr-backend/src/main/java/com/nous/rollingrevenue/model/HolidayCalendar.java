package com.nous.rollingrevenue.model;

import java.time.LocalDate;

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
@Table(name = "holiday_calendar")
@EntityListeners(AuditingEntityListener.class)
@Data
public class HolidayCalendar extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holiday_id")
	private Long holidayId;

	@Column(name = "holiday_name")
	private String holidayName;

	@Column(name = "holiday_date")
	private LocalDate holidayDate;

	@Column(name = "holiday_day")
	private String holidayDay;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

}
