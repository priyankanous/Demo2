package com.nous.rollingrevenue.model;

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

@Entity
@Table(name = "fortnightly_meetings_permission")
@EntityListeners(AuditingEntityListener.class)
public class FortnightlyMeetingsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fortnightly_meetings_permission_id")
	private Long fortnightlyMeetingsPermissionId;

	@Column(name = "fortnightly_meetings_permission_all")
	private Boolean isFortnightlyMeetingsPermissionAll;

	@OneToMany(mappedBy = "fortnightlyMeetingsPermission")
	@JsonBackReference
	private List<CalendarPermission> calendarPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "calendar_common_permission_id", referencedColumnName = "calendar_common_permission_id")
	private CalendarCommonPermission calendarCommonPermission;

	public FortnightlyMeetingsPermission() {

	}

	public FortnightlyMeetingsPermission(Long fortnightlyMeetingsPermissionId,
			Boolean isFortnightlyMeetingsPermissionAll, List<CalendarPermission> calendarPermission,
			CalendarCommonPermission calendarCommonPermission) {
		super();
		this.fortnightlyMeetingsPermissionId = fortnightlyMeetingsPermissionId;
		this.isFortnightlyMeetingsPermissionAll = isFortnightlyMeetingsPermissionAll;
		this.calendarPermission = calendarPermission;
		this.calendarCommonPermission = calendarCommonPermission;
	}

	public Long getFortnightlyMeetingsPermissionId() {
		return fortnightlyMeetingsPermissionId;
	}

	public void setFortnightlyMeetingsPermissionId(Long fortnightlyMeetingsPermissionId) {
		this.fortnightlyMeetingsPermissionId = fortnightlyMeetingsPermissionId;
	}

	public Boolean getIsFortnightlyMeetingsPermissionAll() {
		return isFortnightlyMeetingsPermissionAll;
	}

	public void setIsFortnightlyMeetingsPermissionAll(Boolean isFortnightlyMeetingsPermissionAll) {
		this.isFortnightlyMeetingsPermissionAll = isFortnightlyMeetingsPermissionAll;
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
