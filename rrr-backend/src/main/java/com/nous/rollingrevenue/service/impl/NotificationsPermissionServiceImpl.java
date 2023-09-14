package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.NotificationsPermissionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.NotificationsPermission;
import com.nous.rollingrevenue.repository.NotificationsPermissionRepository;
import com.nous.rollingrevenue.service.NotificationsPermissionService;
import com.nous.rollingrevenue.vo.NotificationsPermissionVO;

@Service
@Transactional(readOnly = true)
public class NotificationsPermissionServiceImpl implements NotificationsPermissionService {

	@Autowired
	private NotificationsPermissionRepository notificationsPermissionRepository;

	@Override
	@Transactional
	public void saveNotificationsPermission(NotificationsPermissionVO notificationsPermissionVO) {
		notificationsPermissionRepository.save(NotificationsPermissionConverter
				.convertNotificationsPermissionVOToNotificationsPermission(notificationsPermissionVO));
	}

	@Override
	@Transactional
	public void updateNotificationsPermissionById(Long notificationsPermissionId,
			NotificationsPermissionVO notificationsPermissionVO) {
		NotificationsPermission notificationsPermission = notificationsPermissionRepository
				.findById(notificationsPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + notificationsPermissionId));
		notificationsPermission.setNotificationsPermissionId(notificationsPermissionVO.getNotificationsPermissionId());
		notificationsPermission.setIsAssignOrModifyCcRecipientsRequired(
				notificationsPermissionVO.getIsAssignOrModifyCcRecipientsRequired());
		notificationsPermission
				.setIsActivateOrDeactivateRequired(notificationsPermissionVO.getIsActivateOrDeactivateRequired());
	}

	@Override
	@Transactional
	public void deleteNotificationsPermissionById(Long notificationsPermissionId) {
		notificationsPermissionRepository.findById(notificationsPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + notificationsPermissionId));
		notificationsPermissionRepository.deleteById(notificationsPermissionId);
	}

	@Override
	public List<NotificationsPermissionVO> getNotificationsPermissions() {
		List<NotificationsPermissionVO> notificationsPermissionVO = new ArrayList<>();
		notificationsPermissionRepository.findAll().stream()
				.forEach(notificationsPermission -> notificationsPermissionVO.add(NotificationsPermissionConverter
						.convertNotificationsPermissionToNotificationsPermissionVO(notificationsPermission)));
		return notificationsPermissionVO;
	}

	@Override
	public NotificationsPermissionVO getNotificationsPermissionById(Long notificationsPermissionId) {
		NotificationsPermission notificationsPermission = notificationsPermissionRepository
				.findById(notificationsPermissionId).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + notificationsPermissionId));
		return NotificationsPermissionConverter
				.convertNotificationsPermissionToNotificationsPermissionVO(notificationsPermission);
	}

}
