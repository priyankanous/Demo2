package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FinancialYearRevenue implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, BigInteger> dataMap = new HashMap<>();

	public FinancialYearRevenue() {

	}

	public FinancialYearRevenue(Map<String, BigInteger> dataMap) {
		super();
		this.dataMap = dataMap;
	}

	public Map<String, BigInteger> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, BigInteger> dataMap) {
		this.dataMap = dataMap;
	}

}
