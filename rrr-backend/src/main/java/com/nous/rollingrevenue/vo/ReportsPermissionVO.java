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

	public Long getReportsPermissionId() {
		return reportsPermissionId;
	}

	public void setReportsPermissionId(Long reportsPermissionId) {
		this.reportsPermissionId = reportsPermissionId;
	}

	public boolean isReportsPermissionsAll() {
		return reportsPermissionsAll;
	}

	public void setReportsPermissionsAll(boolean reportsPermissionsAll) {
		this.reportsPermissionsAll = reportsPermissionsAll;
	}

	public boolean isViewAllEntries() {
		return viewAllEntries;
	}

	public void setViewAllEntries(boolean viewAllEntries) {
		this.viewAllEntries = viewAllEntries;
	}

	public BusinessTypeViewPermissionVO getBusinessTypeViewPermissionVO() {
		return businessTypeViewPermissionVO;
	}

	public void setBusinessTypeViewPermissionVO(BusinessTypeViewPermissionVO businessTypeViewPermissionVO) {
		this.businessTypeViewPermissionVO = businessTypeViewPermissionVO;
	}

	public SBUClientViewPermissionVO getSbuClientViewPermissionVO() {
		return sbuClientViewPermissionVO;
	}

	public void setSbuClientViewPermissionVO(SBUClientViewPermissionVO sbuClientViewPermissionVO) {
		this.sbuClientViewPermissionVO = sbuClientViewPermissionVO;
	}

	public ProbabilityTypePermissionVO getProbabilityTypePermissionVO() {
		return probabilityTypePermissionVO;
	}

	public void setProbabilityTypePermissionVO(ProbabilityTypePermissionVO probabilityTypePermissionVO) {
		this.probabilityTypePermissionVO = probabilityTypePermissionVO;
	}

	public RegionViewPermissionVO getRegionViewPermissionVO() {
		return regionViewPermissionVO;
	}

	public void setRegionViewPermissionVO(RegionViewPermissionVO regionViewPermissionVO) {
		this.regionViewPermissionVO = regionViewPermissionVO;
	}

	public BusinessUnitViewPermissionVO getBuViewPermissionVO() {
		return buViewPermissionVO;
	}

	public void setBuViewPermissionVO(BusinessUnitViewPermissionVO buViewPermissionVO) {
		this.buViewPermissionVO = buViewPermissionVO;
	}

	public ClientWisePermissionVO getClientWisePermissionVO() {
		return clientWisePermissionVO;
	}

	public void setClientWisePermissionVO(ClientWisePermissionVO clientWisePermissionVO) {
		this.clientWisePermissionVO = clientWisePermissionVO;
	}

	public ArchiveWisePermissionVO getArchiveWisePermissionVO() {
		return archiveWisePermissionVO;
	}

	public void setArchiveWisePermissionVO(ArchiveWisePermissionVO archiveWisePermissionVO) {
		this.archiveWisePermissionVO = archiveWisePermissionVO;
	}

}
