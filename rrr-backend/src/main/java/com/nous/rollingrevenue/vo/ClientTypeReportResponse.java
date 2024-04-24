package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.List;

public class ClientTypeReportResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> labels;

	private List<ClientTypeReportOutDTO> outDTOList;

	private String financialYearName;

	public List<String> getLabels() {
		return labels;
	}

	public List<ClientTypeReportOutDTO> getOutDTOList() {
		return outDTOList;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public void setOutDTOList(List<ClientTypeReportOutDTO> outDTOList) {
		this.outDTOList = outDTOList;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

}
