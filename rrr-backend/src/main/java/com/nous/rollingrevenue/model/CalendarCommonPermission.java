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

@Entity
@Table(name = "calendar_common_permission")
@EntityListeners(AuditingEntityListener.class)
public class CalendarCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "calendar_common_permission_id")
	private Long calendarCommonPermissionId;

	@Column(name = "view")
	private boolean isViewRequired;

	@Column(name = "add")
	private boolean isAddRequired;

	@Column(name = "edit")
	private boolean isEditRequired;

	@Column(name = "delete")
	private boolean isDeleteRequired;

	@OneToMany(mappedBy = "calendarCommonPermission")
	@JsonBackReference
	private List<HolidayCalendarPermission> holidayCalendarPermission = new ArrayList<>();

	@OneToMany(mappedBy = "calendarCommonPermission")
	@JsonBackReference
	private List<FortnightlyMeetingsPermission> fortnightlyMeetingsPermission = new ArrayList<>();

	@OneToMany(mappedBy = "calendarCommonPermission")
	@JsonBackReference
	private List<BDMMeetingsPermission> bdmMeetingsPermission = new ArrayList<>();

	public CalendarCommonPermission() {

	}

	public CalendarCommonPermission(Long calendarCommonPermissionId, boolean isViewRequired, boolean isAddRequired,
			boolean isEditRequired, boolean isDeleteRequired, List<HolidayCalendarPermission> holidayCalendarPermission,
			List<FortnightlyMeetingsPermission> fortnightlyMeetingsPermission,
			List<BDMMeetingsPermission> bdmMeetingsPermission) {
		super();
		this.calendarCommonPermissionId = calendarCommonPermissionId;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isEditRequired = isEditRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.holidayCalendarPermission = holidayCalendarPermission;
		this.fortnightlyMeetingsPermission = fortnightlyMeetingsPermission;
		this.bdmMeetingsPermission = bdmMeetingsPermission;
	}

	public Long getCalendarCommonPermissionId() {
		return calendarCommonPermissionId;
	}

	public void setCalendarCommonPermissionId(Long calendarCommonPermissionId) {
		this.calendarCommonPermissionId = calendarCommonPermissionId;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isAddRequired() {
		return isAddRequired;
	}

	public void setAddRequired(boolean isAddRequired) {
		this.isAddRequired = isAddRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public List<HolidayCalendarPermission> getHolidayCalendarPermission() {
		return holidayCalendarPermission;
	}

	public void setHolidayCalendarPermission(List<HolidayCalendarPermission> holidayCalendarPermission) {
		this.holidayCalendarPermission = holidayCalendarPermission;
	}

	public List<FortnightlyMeetingsPermission> getFortnightlyMeetingsPermission() {
		return fortnightlyMeetingsPermission;
	}

	public void setFortnightlyMeetingsPermission(List<FortnightlyMeetingsPermission> fortnightlyMeetingsPermission) {
		this.fortnightlyMeetingsPermission = fortnightlyMeetingsPermission;
	}

	public List<BDMMeetingsPermission> getBdmMeetingsPermission() {
		return bdmMeetingsPermission;
	}

	public void setBdmMeetingsPermission(List<BDMMeetingsPermission> bdmMeetingsPermission) {
		this.bdmMeetingsPermission = bdmMeetingsPermission;
	}

}
