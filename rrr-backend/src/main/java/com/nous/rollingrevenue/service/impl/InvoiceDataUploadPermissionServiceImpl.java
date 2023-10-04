package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.InvoiceDataUploadPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.InvoiceDataUploadPermission;
import com.nous.rollingrevenue.repository.InvoiceDataUploadPermissionRepository;
import com.nous.rollingrevenue.service.InvoiceDataUploadPermissionService;
import com.nous.rollingrevenue.vo.InvoiceDataUploadPermissionVO;

@Service
@Transactional(readOnly = true)
public class InvoiceDataUploadPermissionServiceImpl implements InvoiceDataUploadPermissionService {

	@Autowired
	private InvoiceDataUploadPermissionRepository invoiceDataUploadPermissionRepository;

	@Override
	@Transactional
	public void saveInvoiceDataUploadPermission(InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO) {
//		invoiceDataUploadPermissionRepository.save(InvoiceDataUploadPermissionConverter
//				.convertInvoiceDataUploadPermissionVOToInvoiceDataUploadPermission(invoiceDataUploadPermissionVO));
	}

	@Override
	@Transactional
	public void updateInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId,
			InvoiceDataUploadPermissionVO invoiceDataUploadPermissionVO) {
		InvoiceDataUploadPermission invoiceDataUploadPermission = invoiceDataUploadPermissionRepository
				.findById(invoiceDataUploadPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + invoiceDataUploadPermissionId));
		invoiceDataUploadPermission
				.setInvoiceDataUploadPermissionId(invoiceDataUploadPermissionVO.getInvoiceDataUploadPermissionId());
		invoiceDataUploadPermission.setIsViewListRequired(invoiceDataUploadPermissionVO.getIsViewListRequired());
		invoiceDataUploadPermission.setIsUploadDataRequired(invoiceDataUploadPermissionVO.getIsUploadDataRequired());
//		invoiceDataUploadPermission
//				.setIsMarkMisPublishedRequired(invoiceDataUploadPermissionVO.getIsMarkMisPublishedRequired());
	}

	@Override
	@Transactional
	public void deleteInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId) {
		invoiceDataUploadPermissionRepository.findById(invoiceDataUploadPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + invoiceDataUploadPermissionId));
		invoiceDataUploadPermissionRepository.deleteById(invoiceDataUploadPermissionId);
	}

	@Override
	public List<InvoiceDataUploadPermissionVO> getInvoiceDataUploadPermissions() {
		List<InvoiceDataUploadPermissionVO> invoiceDataUploadPermissionVO = new ArrayList<>();
//		invoiceDataUploadPermissionRepository.findAll().stream().forEach(
//				invoiceDataUploadPermission -> invoiceDataUploadPermissionVO.add(InvoiceDataUploadPermissionConverter
//						.convertInvoiceDataUploadPermissionToInvoiceDataUploadPermissionVO(
//								invoiceDataUploadPermission)));
		return invoiceDataUploadPermissionVO;
	}

	@Override
	public InvoiceDataUploadPermissionVO getInvoiceDataUploadPermissionById(Long invoiceDataUploadPermissionId) {
		InvoiceDataUploadPermission invoiceDataUploadPermission = invoiceDataUploadPermissionRepository
				.findById(invoiceDataUploadPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + invoiceDataUploadPermissionId));
//		return InvoiceDataUploadPermissionConverter
//				.convertInvoiceDataUploadPermissionToInvoiceDataUploadPermissionVO(invoiceDataUploadPermission);
		return null;
	}

}
