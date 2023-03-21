package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public StatusVO saveStatus(StatusVO statusVO) {
		Status status = statusRepository.save(StatusConverter.convertStatusVOToStatus(statusVO));
		return StatusConverter.convertStatusToStatusVO(status);
	}

	@Override
	@Transactional
	@CacheEvict(value = "status", key = "#statusId")
	public void deleteStatusById(Long statusId) {
		statusRepository.findById(statusId)
				.orElseThrow(() -> new RecordNotFoundException("Status not exist with id:" + statusId));
		statusRepository.deleteById(statusId);
	}

	@Override
	@Cacheable(value = "status", key = "#statusId")
	public StatusVO getStatusById(Long statusId) {
		Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RecordNotFoundException("Status not exist with id:" + statusId));
		return StatusConverter.convertStatusToStatusVO(status);
	}

	@Override
	@Transactional
	@CachePut(value = "status", key = "#statusId")
	public StatusVO updateStatus(Long statusId, StatusVO statusVO) {
		Status status = statusRepository.findById(statusId)
				.orElseThrow(() -> new RecordNotFoundException("Status not exist with id:" + statusId));
		status.setStatusName(statusVO.getStatusName());
		status.setStatusDisplayName(statusVO.getStatusDisplayName());
		return StatusConverter.convertStatusToStatusVO(statusRepository.save(status));
	}

}
