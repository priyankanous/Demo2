package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.OpportunityConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.repository.OpportunityRepository;
import com.nous.rollingrevenue.service.OpportunityService;
import com.nous.rollingrevenue.vo.OpportunityVO;

@Service
@Transactional(readOnly = true)
public class OpportunityServiceImpl implements OpportunityService {

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Override
	public List<OpportunityVO> getAllOpportunity() {
		List<OpportunityVO> opportunityVOs = new ArrayList<>();
		opportunityRepository.findAll().stream().forEach(opportunity -> opportunityVOs
				.add(OpportunityConverter.convertOpportunityToOpportunityVO(opportunity)));
		return opportunityVOs;
	}

	@Override
	@Transactional
	public OpportunityVO saveOpportunity(OpportunityVO opportunityVO) {
		Opportunity opportunity = opportunityRepository
				.save(OpportunityConverter.convertOpportunityVOToOpportunity(opportunityVO));
		return OpportunityConverter.convertOpportunityToOpportunityVO(opportunity);
	}

	@Override
	@Transactional
	@CacheEvict(value = "opportunity", key = "#opportunityId")
	public void deleteOpportunityById(Long opportunityId) {
		opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException("Opportunity not exist with id:" + opportunityId));
		opportunityRepository.deleteById(opportunityId);

	}

	@Override
	@Cacheable(value = "opportunity", key = "#opportunityId")
	public OpportunityVO getOpportunityById(Long opportunityId) {
		Opportunity opportunity = opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException("Opportunity not exist with id:" + opportunityId));
		return OpportunityConverter.convertOpportunityToOpportunityVO(opportunity);
	}

	@Override
	@Transactional
	@CachePut(value = "opportunity", key = "#opportunityId")
	public OpportunityVO updateOpportunity(Long opportunityId, OpportunityVO opportunityVO) {
		Opportunity opportunity = opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException("Opportunity not exist with id:" + opportunityId));
		opportunity.setChildOfAccount(opportunityVO.getChildOfAccount());
		opportunity.setProjectCode(opportunityVO.getProjectCode());
		opportunity.setOpportunityName(opportunityVO.getOpportunityName());
		opportunity.setProjectEndDate(opportunityVO.getProjectEndDate());
		opportunity.setProjectStartDate(opportunityVO.getProjectStartDate());
		return OpportunityConverter.convertOpportunityToOpportunityVO(opportunityRepository.save(opportunity));
	}

}
