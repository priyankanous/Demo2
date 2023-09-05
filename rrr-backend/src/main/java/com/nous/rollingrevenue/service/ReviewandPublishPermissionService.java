package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.ReviewandPublishPermissionVO;

public interface ReviewandPublishPermissionService {

	/**
	 * Get all the ReviewandPublishPermission
	 * 
	 * @return List of all ReviewandPublishPermission in the database
	 */
	public List<ReviewandPublishPermissionVO> getAllReviewandPublishPermission();

	/**
	 * Add an ReviewandPublishPermission to the database
	 * 
	 * @param ReviewandPublishPermissionVo
	 * 
	 */
	public void saveReviewandPublishPermission(ReviewandPublishPermissionVO reviewandPublishPermissionVO);

	/**
	 * Delete an ReviewandPublishPermission record by given Id
	 * 
	 * @param ReviewandPublishPermission The ReviewandPublishPermissionId of the
	 *                                   ReviewandPublishPermission to be deleted.
	 *                                   Throws {@link RecordNotFoundException} if
	 *                                   no match is found
	 */
	public void deleteReviewandPublishPermissionById(Long ReviewandPublishPermissionId);

	/**
	 * Get the ReviewandPublishPermission details by given Id
	 * 
	 * @param ReviewandPublishPermissionId The ReviewandPublishPermissionId for
	 *                                     retrieving the details
	 * @return The ReviewandPublishPermission details matching the
	 *         ReviewandPublishPermission id. Throws {@link RecordNotFoundException}
	 *         if no match is found
	 */
	public ReviewandPublishPermissionVO getReviewandPublishPermissionById(Long reviewandPublishPermissionId);

	/**
	 * Update an ReviewandPublishPermission to the database by given Id
	 * 
	 * @param ReviewandPublishPermissionId,ReviewandPublishPermissionVO
	 * 
	 */
	public void updateReviewandPublishPermission(Long ReviewandPublishPermissionId,
			ReviewandPublishPermissionVO reviewandPublishPermissionVO);

}
