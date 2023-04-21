package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.StrategicBusinessUnitHeadConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitHeadRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.service.StrategicBusinessUnitHeadService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitHeadVO;

@Service
@Transactional(readOnly = true)
public class StrategicBusinessUnitHeadServiceImpl implements StrategicBusinessUnitHeadService {

	@Autowired
	private StrategicBusinessUnitHeadRepository sbuHeadRepository;
	
	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;

	@Override
	public List<StrategicBusinessUnitHeadVO> getAllSBUHead() {
		List<StrategicBusinessUnitHeadVO> sbuHeadVO = new ArrayList<>();
		sbuHeadRepository.findAll().stream().forEach(
				sbuHead -> sbuHeadVO.add(StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHead)));
		return sbuHeadVO;
	}

	@Override
	@Transactional
	public void saveSBUHead(StrategicBusinessUnitHeadVO sbuHeadVO) {
		StrategicBusinessUnitHead sbuHead = StrategicBusinessUnitHeadConverter.convertSBUHeadVOToSBUHead(sbuHeadVO);
		StrategicBusinessUnit sbu =  sbuRepository.findById(sbuHeadVO.getStrategicBusinessUnit().getSbuId()).orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "StrategicBusinessUnit not exist"));
		sbuHead.setStrategicbusinessUnit(sbu);
		sbuHeadRepository.save(sbuHead);
	}

	@Override
	@Transactional
	public void deleteSBUHeadById(Long sbuHeadId) {
		sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuHeadId));
		sbuHeadRepository.deleteById(sbuHeadId);

	}

	@Override
	public StrategicBusinessUnitHeadVO getSBUHeadById(Long sbuHeadId) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuHeadId));
		return StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(sbuHead);
	}

	@Override
	@Transactional
	public void updateSBUHead(Long sbuHeadId, StrategicBusinessUnitHeadVO sbuHeadVO) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuHeadId));
		sbuHead.setSbuHeadName(sbuHeadVO.getSbuHeadName());
		sbuHead.setSbuHeadDisplayName(sbuHeadVO.getSbuHeadDisplayName());
		StrategicBusinessUnit sbu =  sbuRepository.findById(sbuHeadVO.getStrategicBusinessUnit().getSbuId()).orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "StrategicBusinessUnit not exist"));
		sbuHead.setStrategicbusinessUnit(sbu);
		sbuHead.setActiveFrom(sbuHeadVO.getActiveFrom());
		sbuHead.setActiveUntil(sbuHeadVO.getActiveUntil());
		sbuHeadRepository.save(sbuHead);
	}

	@Override
	public List<StrategicBusinessUnitHeadVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<StrategicBusinessUnitHeadVO> strategicBusinessUnitHeadVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<StrategicBusinessUnitHead> pageResult = sbuHeadRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				strategicBusinessUnitHeadVOs.add(StrategicBusinessUnitHeadConverter.convertSBUHeadToSBUHeadVO(e));
			});
			return strategicBusinessUnitHeadVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long sbuHeadId) {
		StrategicBusinessUnitHead sbuHead = sbuHeadRepository.findById(sbuHeadId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuHeadId));
		sbuHead.setActive(!sbuHead.isActive());
		sbuHeadRepository.save(sbuHead);
	}

}
