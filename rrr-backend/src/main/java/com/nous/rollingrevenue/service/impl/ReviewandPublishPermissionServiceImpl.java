package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.ReviewandPublishPermission;
import com.nous.rollingrevenue.repository.ReviewandPublishPermissionRepository;
import com.nous.rollingrevenue.service.ReviewandPublishPermissionService;
import com.nous.rollingrevenue.vo.ReviewandPublishPermissionVO;

@Service
@Transactional(readOnly = true)
public class ReviewandPublishPermissionServiceImpl implements ReviewandPublishPermissionService {

	@Autowired
	private ReviewandPublishPermissionRepository reviewandPublishPermissionRepository;

	@Override
	public List<ReviewandPublishPermissionVO> getAllReviewandPublishPermission() {
		List<ReviewandPublishPermissionVO> reviewandPublishPermissionsVO = new ArrayList<>();
//		reviewandPublishPermissionRepository.findAll().stream().forEach(
//				reviewandPublishPermission -> reviewandPublishPermissionsVO.add(ReviewandPublishPermissionConverter
//						.convertReviewandPublishPermissionToReviewandPublishPermissionVO(reviewandPublishPermission)));
		return reviewandPublishPermissionsVO;
	}

	@Override
	@Transactional
	public void saveReviewandPublishPermission(ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
//		reviewandPublishPermissionRepository.save(ReviewandPublishPermissionConverter
//				.convertReviewandPublishPermissionVOToReviewandPublishPermission(reviewandPublishPermissionVO));

	}

	@Override
	public void deleteReviewandPublishPermissionById(Long reviewandPublishPermissionId) {
		reviewandPublishPermissionRepository.findById(reviewandPublishPermissionId).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + reviewandPublishPermissionId));
		reviewandPublishPermissionRepository.deleteById(reviewandPublishPermissionId);

	}

	@Override
	public ReviewandPublishPermissionVO getReviewandPublishPermissionById(Long reviewandPublishPermissionId) {
		ReviewandPublishPermission reviewandPublishPermission = reviewandPublishPermissionRepository
				.findById(reviewandPublishPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + reviewandPublishPermissionId));
//		return ReviewandPublishPermissionConverter
//				.convertReviewandPublishPermissionToReviewandPublishPermissionVO(reviewandPublishPermission);
		return null;
	}

	@Override
	@Transactional
	public void updateReviewandPublishPermission(Long reviewandPublishPermissionId,
			ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
		ReviewandPublishPermission reviewandPublishPermission = reviewandPublishPermissionRepository
				.findById(reviewandPublishPermissionId).orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + reviewandPublishPermissionId));
//		reviewandPublishPermission.setMarkPublishRequired(reviewandPublishPermissionVO.getIsMarkPublishRequired());
//		reviewandPublishPermission
//				.setReviewListViewRequired(reviewandPublishPermissionVO.getIsReviewListViewRequired());
		reviewandPublishPermissionRepository.save(reviewandPublishPermission);
	}

}
