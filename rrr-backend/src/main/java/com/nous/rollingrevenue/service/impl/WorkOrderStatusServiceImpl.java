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
	public void deleteWorkOrderStatusById(Long woStatusId) {
		woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		woStatusRepository.deleteById(woStatusId);
	}

	@Override
	public WorkOrderStatusVO getWorkOrderStatusById(Long woStatusId) {
		WorkOrderStatus woStatus = woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatus);
	}

	@Override
	@Transactional
	public WorkOrderStatusVO updateWorkOrderStatus(Long woStatusId, WorkOrderStatusVO woStatusVO) {
		WorkOrderStatus woStatus = woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		woStatus.setWoStatusName(woStatusVO.getWoStatusName());
		woStatus.setWoStatusDisplayName(woStatusVO.getWoStatusDisplayName());
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatusRepository.save(woStatus));
	}

	@Override
	public List<WorkOrderStatusVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<WorkOrderStatusVO> workOrderStatusVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<WorkOrderStatus> pageResult = woStatusRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				workOrderStatusVOs.add(WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(e));
			});
			return workOrderStatusVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public WorkOrderStatusVO activateOrDeactivateById(Long woStatusId) {
		WorkOrderStatus woStatus = woStatusRepository.findById(woStatusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + woStatusId));
		woStatus.setActive(!woStatus.isActive());
		return WorkOrderStatusConverter.convertWorkOrderStatusToWorkOrderStatusVO(woStatusRepository.save(woStatus));
	}

}
