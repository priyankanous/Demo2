package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.List;

public class SBUClientTypeReportResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> labels;

	private List<SBUClientTypeReportOutDTO> outDTOList;

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

	public List<SBUClientTypeReportOutDTO> getOutDTOList() {
		return outDTOList;
	}

	public void setOutDTOList(List<SBUClientTypeReportOutDTO> outDTOList) {
		this.outDTOList = outDTOList;
	}
 

}
