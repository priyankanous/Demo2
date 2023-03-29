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
import com.nous.rollingrevenue.convertor.WorkOrderStatusConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.WorkOrderStatus;
import com.nous.rollingrevenue.repository.WorkOrderStatusRepository;
import com.nous.rollingrevenue.service.WorkOrderStatusService;
import com.nous.rollingrevenue.vo.WorkOrderStatusVO;

@Service
@Transactional(readOnly = true)
public class WorkOrderStatusServiceImpl implements WorkOrderStatusService {

	@Autowired
	private WorkOrderStatusRepository woStatusRepository;

	@Override
	public List<WorkOrderStatusVO> getAllWorkOrderStatus() {
		List<WorkOrderStatusVO> woStatusVO = new ArrayList<>();
		woStatusRepository.findAll().stream().forEach(woStatus -> woStatusVO
				.add(WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatus)));
		return woStatusVO;
	}

	@Override
	@Transactional
	public WorkOrderStatusVO saveWorkOrderStatus(WorkOrderStatusVO woStatusVO) {
		WorkOrderStatus woStatus = woStatusRepository
				.save(WorkOrderStatusConverter.convertWorkOrderStatusVOToWorkOrderStatus(woStatusVO));
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatus);
	}

	@Override
	@Transactional
	@CacheEvict(value = "wostatus", key = "#woStatusId")
	public void deleteWorkOrderStatusById(Long woStatusId) {
		woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		woStatusRepository.deleteById(woStatusId);
	}

	@Override
	@Cacheable(value = "wostatus", key = "#woStatusId")
	public WorkOrderStatusVO getWorkOrderStatusById(Long woStatusId) {
		WorkOrderStatus woStatus = woStatusRepository.findById(woStatusId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatus);
	}

	@Override
	@Transactional
	@CachePut(value = "wostatus", key = "#woStatusId")
	public WorkOrderStatusVO updateWorkOrderStatus(Long woStatusId, WorkOrderStatusVO woStatusVO) {
		WorkOrderStatus woStatus = woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		woStatus.setWoStatusName(woStatusVO.getWoStatusName());
		woStatus.setWoStatusDisplayName(woStatusVO.getWoStatusDisplayName());
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatusRepository.save(woStatus));
	}

}
