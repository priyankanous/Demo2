package com.nous.rollingrevenue.vo;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class CurrencyPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long currencyPermissionId;

	@NotNull(message = "isViewRequired cannot be null or empty")
	private boolean isViewRequired;

	@NotNull(message = "isAddRequired cannot be null or empty")
	private boolean isAddRequired;
	
	@NotNull(message = "isDeleteOrDeactiveRequired cannot be null or empty")
	private boolean isDeleteOrDeactiveRequired;
	
	@NotNull(message = "isSetConversionForFyRequired cannot be null or empty")
	private boolean isSetConversionForFyRequired;
	
	private boolean isActive;

	public CurrencyPermissionVO() {
	
	}

	public CurrencyPermissionVO(Long currencyPermissionId, boolean isViewRequired, boolean isAddRequired,boolean isDeleteOrDeactiveRequired,boolean isSetConversionForFyRequired,boolean isActive) {
		super();
		this.currencyPermissionId = currencyPermissionId;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isDeleteOrDeactiveRequired = isDeleteOrDeactiveRequired;
		this.isSetConversionForFyRequired = isSetConversionForFyRequired;
		this.isActive = isActive;
	}

	public Long getCurrencyPermissionId() {
		return currencyPermissionId;
	}

	public void setCurrencyPermissionId(Long currencyPermissionId) {
		this.currencyPermissionId = currencyPermissionId;
	}

	public boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean getIsAddRequired() {
		return isAddRequired;
	}

	public void setAddRequired(boolean isAddRequired) {
		this.isAddRequired = isAddRequired;
	}

	public boolean getIsDeleteOrDeactiveRequired() {
		return isDeleteOrDeactiveRequired;
	}

	public void setDeleteOrDeactiveRequired(boolean isDeleteOrDeactiveRequired) {
		this.isDeleteOrDeactiveRequired = isDeleteOrDeactiveRequired;
	}

	public boolean getIsSetConversionForFyRequired() {
		return isSetConversionForFyRequired;
	}

	public void setSetConversionForFyRequired(boolean isSetConversionForFyRequired) {
		this.isSetConversionForFyRequired = isSetConversionForFyRequired;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
