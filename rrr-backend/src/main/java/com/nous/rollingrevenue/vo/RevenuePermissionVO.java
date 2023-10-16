package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RevenuePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long revenuePermissionId;

	private boolean revenuePermissionAll;

	private boolean viewAllEntries;

	private RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO;

	private InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO;

	private ReviewandPublishPermissionVO reviewandPublishPermissionVO;

	public Long getRevenuePermissionId() {
		return revenuePermissionId;
	}

	public void setRevenuePermissionId(Long revenuePermissionId) {
		this.revenuePermissionId = revenuePermissionId;
	}

	public boolean isRevenuePermissionAll() {
		return revenuePermissionAll;
	}

	public void setRevenuePermissionAll(boolean revenuePermissionAll) {
		this.revenuePermissionAll = revenuePermissionAll;
	}

	public boolean isViewAllEntries() {
		return viewAllEntries;
	}

	public void setViewAllEntries(boolean viewAllEntries) {
		this.viewAllEntries = viewAllEntries;
	}

	public RollingRevenueEntryPermissionVO getRollingRevenueEntryPermissionVO() {
		return rollingRevenueEntryPermissionVO;
	}

	public void setRollingRevenueEntryPermissionVO(RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO) {
		this.rollingRevenueEntryPermissionVO = rollingRevenueEntryPermissionVO;
	}

	public InvoiceDataUploadPermissionVO getInvoiceDataUploadPermissionVO() {
		return invoiceDataUploadPermissionVO;
	}

	public void setInvoiceDataUploadPermissionVO(InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO) {
		this.invoiceDataUploadPermissionVO = invoiceDataUploadPermissionVO;
	}

	public ReviewandPublishPermissionVO getReviewandPublishPermissionVO() {
		return reviewandPublishPermissionVO;
	}

	public void setReviewandPublishPermissionVO(ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
		this.reviewandPublishPermissionVO = reviewandPublishPermissionVO;
	}

}
