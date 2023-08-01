package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.BillingTypeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BillingType;
import com.nous.rollingrevenue.repository.BillingTypeRepository;
import com.nous.rollingrevenue.service.BillingTypeService;
import com.nous.rollingrevenue.vo.BillingTypeVO;

@Service
public class BillingTypeServiceImpl implements BillingTypeService {

	@Autowired
	BillingTypeRepository billingTypeRepository;

	@Override
	@Transactional
	public void addBillingType(BillingTypeVO billingTypeVO) {
		BillingType billingType = BillingTypeConverter.convertBillingTypeVOToBillingType(billingTypeVO);
		billingTypeRepository.save(billingType);
	}

	@Override
	@Transactional
	public void updateBillingType(Long id, BillingTypeVO billingTypeVO) {
		BillingType billingType = billingTypeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		billingType.setBillingTypeName(billingTypeVO.getBillingTypeName());
		billingType.setBillingTypeDisplayName(billingTypeVO.getBillingTypeDisplayName());
		billingTypeRepository.save(billingType);
	}

	@Override
	public BillingTypeVO getBillingTypeById(Long id) {
		Optional<BillingType> billingTypeOptional = billingTypeRepository.findById(id);
		if (billingTypeOptional.isPresent()) {
			return BillingTypeConverter
					.convertBillingTypeToBillingTypeVO(billingTypeRepository.save(billingTypeOptional.get()));
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
	}

	@Override
	@Transactional
	public void deleteBillingType(Long id) {
			Optional<BillingType> billingTypeOptional = billingTypeRepository.findById(id);
			if (billingTypeOptional.isPresent()) {
				billingTypeRepository.deleteById(id);
			} else {
				throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
			}
		}

	@Override
		public List<BillingTypeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
			List<BillingTypeVO> billingTypesVOs = new ArrayList<>();
			Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
			Page<BillingType> pageResult = billingTypeRepository.findAll(paging);
			if (pageResult.hasContent()) {
				pageResult.getContent().stream().forEach(e -> {
					billingTypesVOs.add(BillingTypeConverter.convertBillingTypeToBillingTypeVO(e));
				});
				return billingTypesVOs;
			}
			return Collections.emptyList();
	}

		@Override
		@Transactional
		public void activateOrDeactivateById(Long id) {
			BillingType billingType = billingTypeRepository.findById(id)
					.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
			billingType.setActive(!billingType.isActive());
			billingTypeRepository.save(billingType);
	
	}
	}

