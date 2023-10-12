package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CommonAdministrationPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commonAdministrationPermissionId;

	private boolean isView;

	private boolean isAdd;

	private boolean isEdit;

	private boolean isActiveOrDeactive;

	private boolean isDelete;

	public Long getCommonAdministrationPermissionId() {
		return commonAdministrationPermissionId;
	}

	public void setCommonAdministrationPermissionId(Long commonAdministrationPermissionId) {
		this.commonAdministrationPermissionId = commonAdministrationPermissionId;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public boolean isActiveOrDeactive() {
		return isActiveOrDeactive;
	}

	public void setActiveOrDeactive(boolean isActiveOrDeactive) {
		this.isActiveOrDeactive = isActiveOrDeactive;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
