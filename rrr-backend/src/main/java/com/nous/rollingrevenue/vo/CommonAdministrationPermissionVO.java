package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CommonAdministrationPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commonAdministrationPermissionId;

	private boolean view;

	private boolean add;

	private boolean edit;

	private boolean activeOrDeactive;

	private boolean delete;

	public Long getCommonAdministrationPermissionId() {
		return commonAdministrationPermissionId;
	}

	public void setCommonAdministrationPermissionId(Long commonAdministrationPermissionId) {
		this.commonAdministrationPermissionId = commonAdministrationPermissionId;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public boolean isAdd() {
		return add;
	}

	public void setAdd(boolean add) {
		this.add = add;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public boolean isActiveOrDeactive() {
		return activeOrDeactive;
	}

	public void setActiveOrDeactive(boolean activeOrDeactive) {
		this.activeOrDeactive = activeOrDeactive;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

}
