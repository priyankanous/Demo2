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
@Table(name = "fortnightly_meeting")
@EntityListeners(AuditingEntityListener.class)
@Data
public class FortnightlyMeeting extends Auditable<String> {

	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetingId;

	@Column(name = "meeting_date")
	private LocalDate meetingDate;

	@Column(name = "meeting_day")
	private String meetingDay;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@Column(name = "meeting_name1")
	private String meetingName1;

	@Column(name = "meeting_name2")
	private String meetingName2;

	@Column(name = "meeting_name3")
	private String meetingName3;

	@Column(name = "meeting_name4")
	private String meetingName4;

}
