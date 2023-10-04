package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class DeleteRevenueResourceResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer resourceCount;

	private String message;

	public Integer getResourceCount() {
		return resourceCount;
	}

	public void setResourceCount(Integer resourceCount) {
		this.resourceCount = resourceCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
