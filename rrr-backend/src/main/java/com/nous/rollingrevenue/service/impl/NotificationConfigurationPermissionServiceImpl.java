//package com.nous.rollingrevenue.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nous.rollingrevenue.common.constant.ErrorConstants;
//import com.nous.rollingrevenue.convertor.NotificationConfigurationPermissionConverter;
//import com.nous.rollingrevenue.exception.RecordNotFoundException;
//import com.nous.rollingrevenue.model.NotificationConfigurationPermission;
//import com.nous.rollingrevenue.repository.NotificationConfigurationPermissionRepository;
//import com.nous.rollingrevenue.service.NotificationConfigurationPermissionService;
//import com.nous.rollingrevenue.vo.NotificationConfigurationPermissionVO;
//
//@Service
//@Transactional(readOnly = true)
//public class NotificationConfigurationPermissionServiceImpl implements NotificationConfigurationPermissionService {
//
//	@Autowired
//	private NotificationConfigurationPermissionRepository notificationConfigurationPermissionRepository;
//
//	@Override
//	@Transactional
//	public void saveNotificationConfigurationPermission(
//			NotificationConfigurationPermissionVO notificationConfigurationPermissionVO) {
//		notificationConfigurationPermissionRepository.save(NotificationConfigurationPermissionConverter
//				.convertNotificationConfigurationPermissionVOToNotificationConfigurationPermission(
//						notificationConfigurationPermissionVO));
//	}
//
//	@Override
//	@Transactional
//	public void updateNotificationConfigurationPermissionById(Long notificationConfigurationPermissionId,
//			NotificationConfigurationPermissionVO notificationConfigurationPermissionVO) {
//		NotificationConfigurationPermission notificationConfigurationPermission = notificationConfigurationPermissionRepository
//				.findById(notificationConfigurationPermissionId).orElseThrow(() -> new RecordNotFoundException(
//						ErrorConstants.RECORD_NOT_EXIST + notificationConfigurationPermissionId));
//		notificationConfigurationPermission.setNotificationConfigurationPermissionId(
//				notificationConfigurationPermissionVO.getNotificationConfigurationPermissionId());
//		notificationConfigurationPermission
//				.setIsViewRequired(notificationConfigurationPermissionVO.getIsViewRequired());
//		notificationConfigurationPermission
//				.setIsEditRequired(notificationConfigurationPermissionVO.getIsEditRequired());
//		notificationConfigurationPermission.setIsDeleteOrDeactivateRequired(
//				notificationConfigurationPermissionVO.getIsDeleteOrDeactivateRequired());
//		notificationConfigurationPermission.setIsCreateNewNotificationEmailTemplateRequired(
//				notificationConfigurationPermissionVO.getIsCreateNewNotificationEmailTemplateRequired());
//		notificationConfigurationPermission
//				.setIsCopyRequired(notificationConfigurationPermissionVO.getIsCopyRequired());
//		notificationConfigurationPermission
//				.setIsAssignRecipientsRequired(notificationConfigurationPermissionVO.getIsAssignRecipientsRequired());
//	}
//
//	@Override
//	@Transactional
//	public void deleteNotificationConfigurationPermissionById(Long notificationConfigurationPermissionId) {
//		notificationConfigurationPermissionRepository.findById(notificationConfigurationPermissionId)
//				.orElseThrow(() -> new RecordNotFoundException(
//						ErrorConstants.RECORD_NOT_EXIST + notificationConfigurationPermissionId));
//		notificationConfigurationPermissionRepository.deleteById(notificationConfigurationPermissionId);
//	}
//
//	@Override
//	public List<NotificationConfigurationPermissionVO> getNotificationConfigurationPermissions() {
//		List<NotificationConfigurationPermissionVO> notificationConfigurationPermissionVO = new ArrayList<>();
//		notificationConfigurationPermissionRepository.findAll().stream()
//				.forEach(notificationConfigurationPermission -> notificationConfigurationPermissionVO
//						.add(NotificationConfigurationPermissionConverter
//								.convertNotificationConfigurationPermissionToNotificationConfigurationPermissionVO(
//										notificationConfigurationPermission)));
//		return notificationConfigurationPermissionVO;
//	}
//
//	@Override
//	public NotificationConfigurationPermissionVO getNotificationConfigurationPermissionById(
//			Long notificationConfigurationPermissionId) {
//		NotificationConfigurationPermission notificationConfigurationPermission = notificationConfigurationPermissionRepository
//				.findById(notificationConfigurationPermissionId).orElseThrow(() -> new RecordNotFoundException(
//						ErrorConstants.RECORD_NOT_EXIST + notificationConfigurationPermissionId));
//		return NotificationConfigurationPermissionConverter
//				.convertNotificationConfigurationPermissionToNotificationConfigurationPermissionVO(
//						notificationConfigurationPermission);
//	}
//
//}
