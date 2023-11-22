package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "bdm_meeting")
@EntityListeners(AuditingEntityListener.class)
@Data
public class BDMMeeting extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_meeting_id")
	private Long bdmMeetingId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager businessDevelopmentManager;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;

	@Column(name = "meeting_name")
	private String meetingName;

	@Column(name = "meeting_date")
	private LocalDate meetingDate;

	@Column(name = "meeting_time")
	private LocalTime meetingTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

}
