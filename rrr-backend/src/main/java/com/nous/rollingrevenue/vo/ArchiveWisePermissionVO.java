package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ArchiveWisePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long archiveWisePermissionId;

	private boolean archiveWisePermissionAll;

	private ReportsCommonPermissionVO reportsCommonPermissionVO;

	public Long getArchiveWisePermissionId() {
		return archiveWisePermissionId;
	}

	public void setArchiveWisePermissionId(Long archiveWisePermissionId) {
		this.archiveWisePermissionId = archiveWisePermissionId;
	}

	public boolean isArchiveWisePermissionAll() {
		return archiveWisePermissionAll;
	}

	public void setArchiveWisePermissionAll(boolean archiveWisePermissionAll) {
		this.archiveWisePermissionAll = archiveWisePermissionAll;
	}

	public ReportsCommonPermissionVO getReportsCommonPermissionVO() {
		return reportsCommonPermissionVO;
	}

	public void setReportsCommonPermissionVO(ReportsCommonPermissionVO reportsCommonPermissionVO) {
		this.reportsCommonPermissionVO = reportsCommonPermissionVO;
	}

}
