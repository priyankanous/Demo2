package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FortnightlyMeetingVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long meetingId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate meetingDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String meetingDay;

	private FinancialYearVO financialYear;

	@NotBlank(message = "MeetingName1 cannot be null or empty")
	private String meetingName1;

	@NotBlank(message = "MeetingName2 cannot be null or empty")
	private String meetingName2;

	@NotBlank(message = "MeetingName3 cannot be null or empty")
	private String meetingName3;

	@NotBlank(message = "MeetingName4 cannot be null or empty")
	private String meetingName4;

	private boolean isActive;

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
