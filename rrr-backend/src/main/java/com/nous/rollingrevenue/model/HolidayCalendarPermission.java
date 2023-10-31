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
@Table(name = "holiday_calendar_permission")
@EntityListeners(AuditingEntityListener.class)
public class HolidayCalendarPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holiday_calendar_permission_id")
	private Long holidayCalendarPermissionId;

	@Column(name = "holiday_calendar_permission_all")
	private boolean isHolidayCalendarPermissionAll;

	@OneToMany(mappedBy = "holidayCalendarPermission")
	@JsonBackReference
	private List<CalendarPermission> calendarPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "calendar_common_permission_id", referencedColumnName = "calendar_common_permission_id")
	private CalendarCommonPermission calendarCommonPermission;

	public HolidayCalendarPermission() {

	}

	public HolidayCalendarPermission(Long holidayCalendarPermissionId, boolean isHolidayCalendarPermissionAll,
			List<CalendarPermission> calendarPermission, CalendarCommonPermission calendarCommonPermission) {
		super();
		this.holidayCalendarPermissionId = holidayCalendarPermissionId;
		this.isHolidayCalendarPermissionAll = isHolidayCalendarPermissionAll;
		this.calendarPermission = calendarPermission;
		this.calendarCommonPermission = calendarCommonPermission;
	}

	public Long getHolidayCalendarPermissionId() {
		return holidayCalendarPermissionId;
	}

	public void setHolidayCalendarPermissionId(Long holidayCalendarPermissionId) {
		this.holidayCalendarPermissionId = holidayCalendarPermissionId;
	}

	public boolean isHolidayCalendarPermissionAll() {
		return isHolidayCalendarPermissionAll;
	}

	public void setHolidayCalendarPermissionAll(boolean isHolidayCalendarPermissionAll) {
		this.isHolidayCalendarPermissionAll = isHolidayCalendarPermissionAll;
	}

	public List<CalendarPermission> getCalendarPermission() {
		return calendarPermission;
	}

	public void setCalendarPermission(List<CalendarPermission> calendarPermission) {
		this.calendarPermission = calendarPermission;
	}

	public CalendarCommonPermission getCalendarCommonPermission() {
		return calendarCommonPermission;
	}

	public void setCalendarCommonPermission(CalendarCommonPermission calendarCommonPermission) {
		this.calendarCommonPermission = calendarCommonPermission;
	}

}
