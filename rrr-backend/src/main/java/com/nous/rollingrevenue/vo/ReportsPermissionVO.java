package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ReportsPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long reportsPermissionId;

	private boolean reportsPermissionsAll;

	private boolean viewAllEntries;

	private BusinessTypeViewPermissionVO businessTypeViewPermissionVO;

	private SBUClientViewPermissionVO sbuClientViewPermissionVO;

	private ProbabilityTypePermissionVO probabilityTypePermissionVO;

	private RegionViewPermissionVO regionViewPermissionVO;

	private BusinessUnitViewPermissionVO buViewPermissionVO;

	private ClientWisePermissionVO clientWisePermissionVO;

	private ArchiveWisePermissionVO archiveWisePermissionVO;

}
