package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class BusinessUnitReportRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "ViewType cannot be null or empty")
	@Pattern(regexp = "^(Monthly|Quarterly)$", message = "allowed input view : Monthly or Quarterly")
	private String viewType;
	
	@NotBlank(message = "OutputType cannot be null or empty")
	@Pattern(regexp = "^(Chart|Tabular)$", message = "allowed input Output : Chart or Tabular")
	private String outPutType;


	private BusinessUnitReportInDTO data;

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public BusinessUnitReportInDTO getData() {
		return data;
	}

	public void setData(BusinessUnitReportInDTO data) {
		this.data = data;
	}
	
	public String getOutPutType() {
		return outPutType;
	}
	
	public void setOutPutType(String outPutType) {
		this.outPutType = outPutType;
	}

}
