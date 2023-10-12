package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class LocationPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long locationPermissionId;

	private boolean locationPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getLocationPermissionId() {
		return locationPermissionId;
	}

	public void setLocationPermissionId(Long locationPermissionId) {
		this.locationPermissionId = locationPermissionId;
	}

	public boolean isLocationPermissionAll() {
		return locationPermissionAll;
	}

	public void setLocationPermissionAll(boolean locationPermissionAll) {
		this.locationPermissionAll = locationPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
