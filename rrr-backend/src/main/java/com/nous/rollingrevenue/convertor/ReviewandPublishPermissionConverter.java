package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.ReviewandPublishPermission;
import com.nous.rollingrevenue.vo.ReviewandPublishPermissionVO;

@Component
public class ReviewandPublishPermissionConverter {

	public static ReviewandPublishPermission convertReviewandPublishPermissionVOToReviewandPublishPermission(
			ReviewandPublishPermissionVO reviewandPublishPermissionVO) {
		ReviewandPublishPermission reviewandPublishPermission = new ReviewandPublishPermission();
		if (reviewandPublishPermissionVO != null) {
			reviewandPublishPermission
					.setReviewandPublishPermissionId(reviewandPublishPermissionVO.getReviewandPublishPermissionId());
			reviewandPublishPermission.setMarkPublishRequired(reviewandPublishPermissionVO.getIsMarkPublishRequired());
			reviewandPublishPermission
					.setReviewListViewRequired(reviewandPublishPermissionVO.getIsReviewListViewRequired());
		}
		return reviewandPublishPermission;
	}

	public static ReviewandPublishPermissionVO convertReviewandPublishPermissionToReviewandPublishPermissionVO(
			ReviewandPublishPermission reviewandPublishPermission) {
		ReviewandPublishPermissionVO reviewandPublishPermissionVO = new ReviewandPublishPermissionVO();
		if (reviewandPublishPermission != null) {
			reviewandPublishPermissionVO
					.setReviewandPublishPermissionId(reviewandPublishPermission.getReviewandPublishPermissionId());
			reviewandPublishPermissionVO.setMarkPublishRequired(reviewandPublishPermission.getIsMarkPublishRequired());
			reviewandPublishPermissionVO
					.setReviewListViewRequired(reviewandPublishPermission.getIsReviewListViewRequired());
			reviewandPublishPermissionVO.setActive(reviewandPublishPermission.isActive());
		}
		return reviewandPublishPermissionVO;
	}
}
