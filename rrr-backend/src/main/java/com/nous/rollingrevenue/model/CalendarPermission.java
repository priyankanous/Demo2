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

@Entity
@Table(name = "calendar_permission")
@EntityListeners(AuditingEntityListener.class)
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

	public CalendarPermission() {

	}

	public CalendarPermission(Long calendarPermissionId, boolean calendarPermissionAll,
			boolean isViewAllEntriesRequired, HolidayCalendarPermission holidayCalendarPermission,
			FortnightlyMeetingsPermission fortnightlyMeetingsPermission, BDMMeetingsPermission bdmMeetingsPermission,
			List<Roles> roles) {
		super();
		this.calendarPermissionId = calendarPermissionId;
		this.calendarPermissionAll = calendarPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.holidayCalendarPermission = holidayCalendarPermission;
		this.fortnightlyMeetingsPermission = fortnightlyMeetingsPermission;
		this.bdmMeetingsPermission = bdmMeetingsPermission;
		this.roles = roles;
	}

	public Long getCalendarPermissionId() {
		return calendarPermissionId;
	}

	public void setCalendarPermissionId(Long calendarPermissionId) {
		this.calendarPermissionId = calendarPermissionId;
	}

	public boolean isCalendarPermissionAll() {
		return calendarPermissionAll;
	}

	public void setCalendarPermissionAll(boolean calendarPermissionAll) {
		this.calendarPermissionAll = calendarPermissionAll;
	}

	public boolean isViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public HolidayCalendarPermission getHolidayCalendarPermission() {
		return holidayCalendarPermission;
	}

	public void setHolidayCalendarPermission(HolidayCalendarPermission holidayCalendarPermission) {
		this.holidayCalendarPermission = holidayCalendarPermission;
	}

	public FortnightlyMeetingsPermission getFortnightlyMeetingsPermission() {
		return fortnightlyMeetingsPermission;
	}

	public void setFortnightlyMeetingsPermission(FortnightlyMeetingsPermission fortnightlyMeetingsPermission) {
		this.fortnightlyMeetingsPermission = fortnightlyMeetingsPermission;
	}

	public BDMMeetingsPermission getBdmMeetingsPermission() {
		return bdmMeetingsPermission;
	}

	public void setBdmMeetingsPermission(BDMMeetingsPermission bdmMeetingsPermission) {
		this.bdmMeetingsPermission = bdmMeetingsPermission;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
