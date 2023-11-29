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
import com.nous.rollingrevenue.convertor.StatusConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Status;
import com.nous.rollingrevenue.repository.StatusRepository;
import com.nous.rollingrevenue.service.StatusService;
import com.nous.rollingrevenue.vo.StatusVO;

@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<StatusVO> getAllStatus() {
		List<StatusVO> statusVO = new ArrayList<>();
		statusRepository.findAll().stream()
				.forEach(status -> statusVO.add(StatusConverter.convertStatusToStatusVO(status)));
		return statusVO;
	}

	@Override
	@Transactional
	public void saveStatus(StatusVO statusVO) {
		statusRepository.save(StatusConverter.convertStatusVOToStatus(statusVO));
	}

	@Override
	@Transactional
	public void deleteStatusById(Long statusId) {
		Optional<Status> findById = statusRepository.findById(statusId);
		if (findById.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + statusId);
		} else {
			statusRepository.deleteById(statusId);
		}
	}

	@Override
	public StatusVO getStatusById(Long statusId) {
		Status status = statusRepository.findById(statusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + statusId));
		return StatusConverter.convertStatusToStatusVO(status);
	}

	@Override
	@Transactional
	public void updateStatus(Long statusId, StatusVO statusVO) {
		Status status = statusRepository.findById(statusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + statusId));
		status.setStatusName(statusVO.getStatusName());
		status.setStatusDisplayName(statusVO.getStatusDisplayName());
		statusRepository.save(status);
	}

	@Override
	public List<StatusVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<StatusVO> statusVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Status> pageResult = statusRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> statusVOs.add(StatusConverter.convertStatusToStatusVO(e)));
			return statusVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long statusId) {
		Status status = statusRepository.findById(statusId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + statusId));
		status.setActive(!status.isActive());
		statusRepository.save(status);
	}

}
