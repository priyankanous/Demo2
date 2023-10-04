package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ExplicitPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long explicitPermissionId;

	private boolean explicitPermissionAll;

	private Boolean isCreateRequired;

	private Boolean isEditRequired;

	private Boolean isViewRequired;

	private Boolean isDeleteRequired;

}
