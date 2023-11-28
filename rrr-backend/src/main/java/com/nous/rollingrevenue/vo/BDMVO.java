package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BDMVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long bdmId;

	@NotBlank(message = "BDMName cannot be null or empty")
	private String bdmName;

	@NotBlank(message = "BDMDisplayName cannot be null or empty")
	private String bdmDisplayName;

	@NotNull(message = "activeFrom cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate activeUntil;

	private List<BusinessUnitVO> businessUnits = new ArrayList<>();

	private List<RegionVO> regions = new ArrayList<>();

	private boolean isActive;

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
