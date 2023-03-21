package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.service.BusinessDevelopmentManagerService;
import com.nous.rollingrevenue.vo.BDMVO;

import jakarta.validation.Valid;

@Service
public class BusinessDevelopmentManagerServiceImpl implements BusinessDevelopmentManagerService {

	@Autowired
	BusinessDevelopmentManagerRepository businessDevelopmentManagerRepository;

	@Override
	@Transactional
	public BusinessDevelopmentManager addBDMDetails(BusinessDevelopmentManager bdm) {
		return businessDevelopmentManagerRepository.save(bdm);
	}

	@Override
	@Transactional
	@CachePut(value = "bdm", key = "#bdmId")
	public BusinessDevelopmentManager updateBDMDetails(Long bdmId, BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = businessDevelopmentManagerRepository.findById(bdmId).orElseThrow(
				() -> new RecordNotFoundException("Business Development Manager(BDM) not found for id:" + bdmId));
		bdm.setBdmName(bdmVO.getBdmName());
		bdm.setBdmDisplayName(bdmVO.getBdmDisplayName());
		bdm.setActiveFrom(bdmVO.getActiveFrom());
		bdm.setActiveUntil(bdmVO.getActiveUntil());
		bdm.setLinkedToBusinessUnit(bdmVO.getLinkedToBusinessUnit());
		bdm.setLinkedToRegion(bdmVO.getLinkedToRegion());
		return businessDevelopmentManagerRepository.save(bdm);
	}

	@Override
	@Cacheable(value = "bdm", key = "#bdmId")
	public BusinessDevelopmentManager getBdmById(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		if (bdmOptional.isPresent()) {
			return bdmOptional.get();
		}
		throw new RecordNotFoundException("Business Development Manager(BDM) not found for id:" + bdmId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "bdm", key = "#bdmId")
	public void deleteBDM(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		if (bdmOptional.isPresent()) {
			businessDevelopmentManagerRepository.deleteById(bdmId);
		} else {
			throw new RecordNotFoundException("Business Development Manager(BDM) not found for id:" + bdmId);
		}
	}

	@Override
	public List<BusinessDevelopmentManager> getBDM() {
		return businessDevelopmentManagerRepository.findAll();
	}

}
