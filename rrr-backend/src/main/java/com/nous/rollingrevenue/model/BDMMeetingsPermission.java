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
@Table(name = "bdm_meetings_permission")
@EntityListeners(AuditingEntityListener.class)
public class BDMMeetingsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_meetings_permission_id")
	private Long bdmMeetingsPermissionId;

	@Column(name = "bdm_meetings_permission_all")
	private Boolean isBdmMeetingsPermissionAll;

	@OneToMany(mappedBy = "bdmMeetingsPermission")
	@JsonBackReference
	private List<CalendarPermission> calendarPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "calendar_common_permission_id", referencedColumnName = "calendar_common_permission_id")
	private CalendarCommonPermission calendarCommonPermission;

	public BDMMeetingsPermission() {

	}

	public BDMMeetingsPermission(Long bdmMeetingsPermissionId, Boolean isBdmMeetingsPermissionAll,
			List<CalendarPermission> calendarPermission, CalendarCommonPermission calendarCommonPermission) {
		super();
		this.bdmMeetingsPermissionId = bdmMeetingsPermissionId;
		this.isBdmMeetingsPermissionAll = isBdmMeetingsPermissionAll;
		this.calendarPermission = calendarPermission;
		this.calendarCommonPermission = calendarCommonPermission;
	}

	public Long getBdmMeetingsPermissionId() {
		return bdmMeetingsPermissionId;
	}

	public void setBdmMeetingsPermissionId(Long bdmMeetingsPermissionId) {
		this.bdmMeetingsPermissionId = bdmMeetingsPermissionId;
	}

	public Boolean getIsBdmMeetingsPermissionAll() {
		return isBdmMeetingsPermissionAll;
	}

	public void setIsBdmMeetingsPermissionAll(Boolean isBdmMeetingsPermissionAll) {
		this.isBdmMeetingsPermissionAll = isBdmMeetingsPermissionAll;
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
