package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SBUClientTypeReportInDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "FinancialYearName cannot be null or empty")
	private String financialYearName;

	private Long regionId;

	private Long businessUnitId;

	private Long sbuHeadId;

	private Long bussinessTypeId;

	private Long probabilityTypeId;

	private Long locationId;

	private Long bdmId;

}
