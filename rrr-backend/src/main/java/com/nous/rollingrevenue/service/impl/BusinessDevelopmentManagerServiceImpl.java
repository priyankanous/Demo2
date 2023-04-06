package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.BusinessDevelopmentManagerConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.service.BusinessDevelopmentManagerService;
import com.nous.rollingrevenue.vo.BDMVO;

@Service
public class BusinessDevelopmentManagerServiceImpl implements BusinessDevelopmentManagerService {

	@Autowired
	BusinessDevelopmentManagerRepository businessDevelopmentManagerRepository;

	@Override
	@Transactional
	public BDMVO addBDMDetails(BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = BusinessDevelopmentManagerConverter.convertBdmVOToBdm(bdmVO);
		return BusinessDevelopmentManagerConverter.convertBdmToBdmVO(businessDevelopmentManagerRepository.save(bdm));
	}

	@Override
	@Transactional
	@CachePut(value = "bdm", key = "#bdmId")
	public BDMVO updateBDMDetails(Long bdmId, BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = businessDevelopmentManagerRepository.findById(bdmId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId));
		bdm.setBdmName(bdmVO.getBdmName());
		bdm.setBdmDisplayName(bdmVO.getBdmDisplayName());
		bdm.setActiveFrom(bdmVO.getActiveFrom());
		bdm.setActiveUntil(bdmVO.getActiveUntil());
		bdm.setLinkedToBusinessUnit(bdmVO.getLinkedToBusinessUnit());
		bdm.setLinkedToRegion(bdmVO.getLinkedToRegion());
		return BusinessDevelopmentManagerConverter.convertBdmToBdmVO(businessDevelopmentManagerRepository.save(bdm));
	}

	@Override
	@Cacheable(value = "bdm", key = "#bdmId")
	public BDMVO getBdmById(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		if (bdmOptional.isPresent()) {
			return BusinessDevelopmentManagerConverter.convertBdmToBdmVO(bdmOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "bdm", key = "#bdmId")
	public void deleteBDM(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		if (bdmOptional.isPresent()) {
			businessDevelopmentManagerRepository.deleteById(bdmId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId);
		}
	}

	@Override
	public List<BDMVO> getBDM() {
		List<BDMVO> bdmVOs = new ArrayList<>();
		businessDevelopmentManagerRepository.findAll().stream()
				.forEach(bdm -> bdmVOs.add(BusinessDevelopmentManagerConverter.convertBdmToBdmVO(bdm)));
		return bdmVOs;
	}
	
	@Override
	public List<BDMVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BDMVO> bdmVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<BusinessDevelopmentManager> pageResult = businessDevelopmentManagerRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				bdmVOs.add(BusinessDevelopmentManagerConverter.convertBdmToBdmVO(e));
			});
			return bdmVOs;
		}
		return Collections.emptyList();
	}

}
