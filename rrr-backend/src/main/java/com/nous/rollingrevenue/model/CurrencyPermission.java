package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CurrencyPermission")
@EntityListeners(AuditingEntityListener.class)
public class CurrencyPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_permission_id")
	private Long currencyPermissionId;

	@Column(name = "is_view")
	private boolean isViewRequired;

	@Column(name = "is_Add")
	private boolean isAddRequired;
	
	@Column(name = "is_Delete_Or_Deactive")
	private boolean isDeleteOrDeactiveRequired;
	
	@Column(name = "is_Set_Conversion_For_Fy")
	private boolean isSetConversionForFyRequired;

	public CurrencyPermission() {
		
	}

	public CurrencyPermission(Long currencyPermissionId, boolean isViewRequired, boolean isAddRequired,
			boolean isDeleteOrDeactiveRequired, boolean isSetConversionForFyRequired) {
		super();
		this.currencyPermissionId = currencyPermissionId;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isDeleteOrDeactiveRequired = isDeleteOrDeactiveRequired;
		this.isSetConversionForFyRequired = isSetConversionForFyRequired;
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
	
}
