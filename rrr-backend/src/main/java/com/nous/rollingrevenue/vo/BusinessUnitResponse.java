package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.List;

public class BusinessUnitResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> labels;

	private List<BusinessUnitOutDTO> outDTOList;

	private String financialYearName;

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<BusinessUnitOutDTO> getOutDTOList() {
		return outDTOList;
	}

	public void setOutDTOList(List<BusinessUnitOutDTO> outDTOList) {
		this.outDTOList = outDTOList;
	}

}
