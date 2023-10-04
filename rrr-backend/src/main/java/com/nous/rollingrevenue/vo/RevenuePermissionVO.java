package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RevenuePermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long revenuePermissionId;

	private boolean revenuePermissionAll;

	private boolean isViewAllEntries;

	private RollingRevenueEntryPermissionVO rollingRevenueEntryPermissionVO;

	private InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO;
	
	private ReviewandPublishPermissionVO reviewandPublishPermissionVO;

}
