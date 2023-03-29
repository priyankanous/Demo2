package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.StrategicBusinessUnitConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.service.StrategicBusinessUnitService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;

@Service
@Transactional(readOnly = true)
public class StrategicBusinessUnitServiceImpl implements StrategicBusinessUnitService {
	
	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;

	@Override
	public List<StrategicBusinessUnitVO> getAllSBU() {
		List<StrategicBusinessUnitVO> sbuVO = new ArrayList<>();
		sbuRepository.findAll().stream().forEach(
				sbu -> sbuVO.add(StrategicBusinessUnitConverter.convertSBUToSBUVO(sbu)));
		return sbuVO;
	}

	@Override
	@Transactional
	public StrategicBusinessUnitVO saveSBU(StrategicBusinessUnitVO sbuVO) {
		StrategicBusinessUnit sbu = sbuRepository.save(StrategicBusinessUnitConverter.convertSBUVOToSBU(sbuVO));	
		return StrategicBusinessUnitConverter.convertSBUToSBUVO(sbu);
	}

	@Override
	@Transactional
	@CacheEvict(value = "sbu", key= "#sbuId")
	public void deleteSBUById(Long sbuId) {
		sbuRepository.findById(sbuId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		sbuRepository.deleteById(sbuId);		
	}

	@Override
	@Cacheable(value = "sbu", key = "#sbuId")
	public StrategicBusinessUnitVO getSBUById(Long sbuId) {
		StrategicBusinessUnit sbu = sbuRepository.findById(sbuId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		return StrategicBusinessUnitConverter.convertSBUToSBUVO(sbu);
	}

	@Override
	@Transactional
	@CachePut(value = "sbu", key = "#sbuId")
	public StrategicBusinessUnitVO updateSBU(Long sbuId, StrategicBusinessUnitVO sbuVO) {
		StrategicBusinessUnit sbu = sbuRepository.findById(sbuId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		sbu.setSbuName(sbuVO.getSbuName());
		sbu.setSbuDisplayName(sbuVO.getSbuDisplayName());
		sbu.setBuDisplayName(sbuVO.getBuDisplayName());
		return StrategicBusinessUnitConverter.convertSBUToSBUVO(sbuRepository.save(sbu));
	}

}
