package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.StrategicBusinessUnitHeadConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitHeadRepository;
import com.nous.rollingrevenue.service.StrategicBusinessUnitHeadService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;

@Service
@Transactional(readOnly = true)
public class StrategicBusinessUnitHeadServiceImpl implements StrategicBusinessUnitHeadService {

	@Autowired
	private StrategicBusinessUnitHeadRepository sbuHeadRepository;

	@Override
	public List<StrategicBusinessUnitHeadVO> getAllSBUHead() {
		List<StrategicBusinessUnitHeadVO> sbuHeadVO = new ArrayList<>();
		sbuHeadRepository.findAll().stream().forEach(
				sbuHead -> sbuHeadVO.add(StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHead)));
		return sbuHeadVO;
	}

	@Override
	@Transactional
	public StrategicBusinessUnitHeadVO saveSBUHead(StrategicBusinessUnitHeadVO sbuHeadVO) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository
				.save(StrategicBusinessUnitHeadConverter.convertSBUHeadVOToSBUHead(sbuHeadVO));
		return StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHead);
	}

	@Override
	@Transactional
	@CacheEvict(value = "sbuhead", key= "#sbuHeadId")
	public void deleteSBUHeadById(Long sbuHeadId) {
		sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException("SBUHead not exist with id:" + sbuHeadId));
		sbuHeadRepository.deleteById(sbuHeadId);

	}

	@Override
	@Cacheable(value = "sbuhead", key = "#sbuHeadId")
	public StrategicBusinessUnitHeadVO getSBUHeadById(Long sbuHeadId) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException("SBUHead not exist with id:" + sbuHeadId));
		return StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHead);
	}

	@Override
	@Transactional
	@CachePut(value = "sbuhead", key = "#sbuHeadId")
	public StrategicBusinessUnitHeadVO updateSBUHead(Long sbuHeadId, StrategicBusinessUnitHeadVO sbuHeadVO) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException("SBUHead not exist with id:" + sbuHeadId));
		sbuHead.setSbuHeadName(sbuHeadVO.getSbuHeadName());
		sbuHead.setSbuHeadDisplayName(sbuHeadVO.getSbuHeadDisplayName());
		sbuHead.setSbuName(sbuHeadVO.getSbuName());
		sbuHead.setActiveFrom(sbuHeadVO.getActiveFrom());
		sbuHead.setActiveUntil(sbuHeadVO.getActiveUntil());
		return StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHeadRepository.save(sbuHead));
	}

}
