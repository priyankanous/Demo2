package com.nous.rollingrevenue.model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ReviewAndPublishPermission")
@EntityListeners(AuditingEntityListener.class)
public class ReviewandPublishPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Review_and_Publish_Permission_id")
	private Long reviewandPublishPermissionId;

	@Column(name = "is_review_list_view")
	private boolean isReviewListViewRequired;

	@Column(name = "is_mark_publish")
	private boolean isMarkPublishRequired;

	public ReviewandPublishPermission() {
	
	}

	public ReviewandPublishPermission(Long reviewandPublishPermissionId, boolean isReviewListViewRequired,
			boolean isMarkPublishRequired) {
		super();
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
		this.isReviewListViewRequired = isReviewListViewRequired;
		this.isMarkPublishRequired = isMarkPublishRequired;
	}

	public Long getReviewandPublishPermissionId() {
		return reviewandPublishPermissionId;
	}

	public void setReviewandPublishPermissionId(Long reviewandPublishPermissionId) {
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
	}

	public boolean getIsReviewListViewRequired() {
		return isReviewListViewRequired;
	}

	public void setReviewListViewRequired(boolean isReviewListViewRequired) {
		this.isReviewListViewRequired = isReviewListViewRequired;
	}

	public boolean getIsMarkPublishRequired() {
		return isMarkPublishRequired;
	}

	public void setMarkPublishRequired(boolean isMarkPublishRequired) {
		this.isMarkPublishRequired = isMarkPublishRequired;
	}


}
