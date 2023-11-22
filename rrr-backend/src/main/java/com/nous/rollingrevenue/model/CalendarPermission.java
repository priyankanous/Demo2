package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "calendar_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CalendarPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_permission_id")
	private Long calendarPermissionId;

	@Column(name = "calendar_permission_all")
	private boolean calendarPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "holiday_calendar_permission_id", referencedColumnName = "holiday_calendar_permission_id")
	private HolidayCalendarPermission holidayCalendarPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "fortnightly_meetings_permission_id", referencedColumnName = "fortnightly_meetings_permission_id")
	private FortnightlyMeetingsPermission fortnightlyMeetingsPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "bdm_meetings_permission_id", referencedColumnName = "bdm_meetings_permission_id")
	private BDMMeetingsPermission bdmMeetingsPermission;

	@OneToMany(mappedBy = "calendarPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

}
