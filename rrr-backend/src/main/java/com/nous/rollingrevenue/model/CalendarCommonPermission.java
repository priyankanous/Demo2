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
@Table(name = "calendar_common_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
