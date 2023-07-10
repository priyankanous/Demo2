package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SBUClientTypeReportRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "ViewType cannot be null or empty")
	@Pattern(regexp = "^(Monthly|Quarterly)$", message = "allowed input view : Monthly or Quarterly")
	private String viewType;

	private SBUClientTypeReportInDTO data;

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public SBUClientTypeReportInDTO getData() {
		return data;
	}

	public void setData(SBUClientTypeReportInDTO data) {
		this.data = data;
	}
 

}
