package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RollingRevenueResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TandMRevenueEntryVO tmRevenueEntryVO;

	private FPRevenueEntryVO fpRevenueEntryVO;

	public TandMRevenueEntryVO getTmRevenueEntryVO() {
		return tmRevenueEntryVO;
	}

	public void setTmRevenueEntryVO(TandMRevenueEntryVO tmRevenueEntryVO) {
		this.tmRevenueEntryVO = tmRevenueEntryVO;
	}

	public FPRevenueEntryVO getFpRevenueEntryVO() {
		return fpRevenueEntryVO;
	}

	public void setFpRevenueEntryVO(FPRevenueEntryVO fpRevenueEntryVO) {
		this.fpRevenueEntryVO = fpRevenueEntryVO;
	}

}
