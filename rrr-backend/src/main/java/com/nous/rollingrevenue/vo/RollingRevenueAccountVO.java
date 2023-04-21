package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.List;

public class RollingRevenueAccountVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String businessUnit;

	private String strategicBusinessUnit;

	private String strategicBusinessUnitHead;

	private String bdm;

	private String businessType;

	private String account;

	private String region;

	private String location;

	private String probability;

	private String coc;

	private String status;

	private List<ProjectCodesVO> projectCodeList;

	private MonthlyFinancialYearVO monthlyFinancialYearVO;

	private String financialYearName;

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getStrategicBusinessUnit() {
		return strategicBusinessUnit;
	}

	public void setStrategicBusinessUnit(String strategicBusinessUnit) {
		this.strategicBusinessUnit = strategicBusinessUnit;
	}

	public String getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(String strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public String getBdm() {
		return bdm;
	}

	public void setBdm(String bdm) {
		this.bdm = bdm;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getCoc() {
		return coc;
	}

	public void setCoc(String coc) {
		this.coc = coc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProjectCodesVO> getProjectCodeList() {
		return projectCodeList;
	}

	public void setProjectCodeList(List<ProjectCodesVO> projectCodeList) {
		this.projectCodeList = projectCodeList;
	}

	public MonthlyFinancialYearVO getMonthlyFinancialYearVO() {
		return monthlyFinancialYearVO;
	}

	public void setMonthlyFinancialYearVO(MonthlyFinancialYearVO monthlyFinancialYearVO) {
		this.monthlyFinancialYearVO = monthlyFinancialYearVO;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

}
