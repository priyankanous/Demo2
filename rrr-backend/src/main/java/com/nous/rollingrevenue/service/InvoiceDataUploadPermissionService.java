package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.InvoiceDataUploadPermissionVO;

import jakarta.validation.Valid;

public interface InvoiceDataUploadPermissionService {

	/**
	 * Add an InvoiceDataUploadPermission to the database
	 * 
	 * @param InvoiceDataUploadPermissionVO
	 * 
	 */
	void saveInvoiceDataUploadPermission(InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO);

	/**
	 * Update an InvoiceDataUploadPermission to the database by given
	 * invoiceDataUploadPermissionId
	 * 
	 * @param InvoiceDataUploadPermissionId, InvoiceDataUploadPermissionVO
	 * 
	 */
	void updateInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId,
			@Valid InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO);

	/**
	 * Delete an InvoiceDataUploadPermission record by given
	 * invoiceDataUploadPermissionId
	 * 
	 * @param invoiceDataUploadPermissionId The invoiceDataUploadPermissionId of the
	 *                                      InvoiceDataUploadPermission to be
	 *                                      deleted. Throws
	 *                                      {@link RecordNotFoundException} if no
	 *                                      match is found
	 */
	void deleteInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId);

	/**
	 * Get all the InvoiceDataUploadPermission
	 * 
	 * @return List of all InvoiceDataUploadPermissions in the database
	 */
	List<InvoiceDataUploadPermissionVO> getInvoiceDataUploadPermissions();

	/**
	 * Get the InvoiceDataUploadPermission details by given
	 * invoiceDataUploadPermissionId
	 * 
	 * @param invoiceDataUploadPermissionId The invoiceDataUploadPermissionId for
	 *                                      retrieving the details
	 * @return The InvoiceDataUploadPermission details matching the
	 *         invoiceDataUploadPermission Id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public InvoiceDataUploadPermissionVO getInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId);
}
