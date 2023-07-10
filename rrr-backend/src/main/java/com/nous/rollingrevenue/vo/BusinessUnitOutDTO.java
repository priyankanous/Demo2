package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class BusinessUnitOutDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String label;
	private String stack;
	private List<BigInteger> data;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public List<BigInteger> getData() {
		return data;
	}

	public void setData(List<BigInteger> data) {
		this.data = data;
	}

}
