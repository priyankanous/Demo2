package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.List;

public class RegionResponse  implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	private List<String> labels;

	private List<RegionOutDTO> outDTOList;

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

	public List<RegionOutDTO> getOutDTOList() {
		return outDTOList;
	}

	public void setOutDTOList(List<RegionOutDTO> outDTOList) {
		this.outDTOList = outDTOList;
	}

}
