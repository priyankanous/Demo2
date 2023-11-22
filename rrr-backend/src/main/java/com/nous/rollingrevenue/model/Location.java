package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "location")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Location extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long locationId;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "location_display_name")
	private String locationDisplayName;

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<HolidayCalendar> holidayCalendar = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

}
