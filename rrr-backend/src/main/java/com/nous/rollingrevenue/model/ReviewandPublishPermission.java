package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ReviewAndPublishPermission")
@EntityListeners(AuditingEntityListener.class)
public class ReviewandPublishPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_and_publish_permission_id")
	private Long reviewandPublishPermissionId;

	@Column(name = "review_and_publish_permission_all")
	private boolean isReviewAndPublishPermissionAll;

	@Column(name = "view")
	private boolean isViewRequired;

	@Column(name = "review_and_submit")
	private boolean isReviewAndSubmitRequired;

	@Column(name = "save")
	private boolean isSaveRequired;

	@Column(name = "copy")
	private boolean isCopyRequired;

	@Column(name = "edit")
	private boolean isEditRequired;

	@Column(name = "publish")
	private boolean isPublishRequired;

	@Column(name = "save_recipients")
	private boolean isSaveRecipientsRequired;

	@OneToMany(mappedBy = "reviewandPublishPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

	public ReviewandPublishPermission() {

	}

	public ReviewandPublishPermission(Long reviewandPublishPermissionId, boolean isReviewAndPublishPermissionAll,
			boolean isViewRequired, boolean isReviewAndSubmitRequired, boolean isSaveRequired, boolean isCopyRequired,
			boolean isEditRequired, boolean isPublishRequired, boolean isSaveRecipientsRequired,
			List<RevenuePermission> revenuePermission) {
		super();
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
		this.isReviewAndPublishPermissionAll = isReviewAndPublishPermissionAll;
		this.isViewRequired = isViewRequired;
		this.isReviewAndSubmitRequired = isReviewAndSubmitRequired;
		this.isSaveRequired = isSaveRequired;
		this.isCopyRequired = isCopyRequired;
		this.isEditRequired = isEditRequired;
		this.isPublishRequired = isPublishRequired;
		this.isSaveRecipientsRequired = isSaveRecipientsRequired;
		this.revenuePermission = revenuePermission;
	}

	public Long getReviewandPublishPermissionId() {
		return reviewandPublishPermissionId;
	}

	public void setReviewandPublishPermissionId(Long reviewandPublishPermissionId) {
		this.reviewandPublishPermissionId = reviewandPublishPermissionId;
	}

	public boolean isReviewAndPublishPermissionAll() {
		return isReviewAndPublishPermissionAll;
	}

	public void setReviewAndPublishPermissionAll(boolean isReviewAndPublishPermissionAll) {
		this.isReviewAndPublishPermissionAll = isReviewAndPublishPermissionAll;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isReviewAndSubmitRequired() {
		return isReviewAndSubmitRequired;
	}

	public void setReviewAndSubmitRequired(boolean isReviewAndSubmitRequired) {
		this.isReviewAndSubmitRequired = isReviewAndSubmitRequired;
	}

	public boolean isSaveRequired() {
		return isSaveRequired;
	}

	public void setSaveRequired(boolean isSaveRequired) {
		this.isSaveRequired = isSaveRequired;
	}

	public boolean isCopyRequired() {
		return isCopyRequired;
	}

	public void setCopyRequired(boolean isCopyRequired) {
		this.isCopyRequired = isCopyRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isPublishRequired() {
		return isPublishRequired;
	}

	public void setPublishRequired(boolean isPublishRequired) {
		this.isPublishRequired = isPublishRequired;
	}

	public boolean isSaveRecipientsRequired() {
		return isSaveRecipientsRequired;
	}

	public void setSaveRecipientsRequired(boolean isSaveRecipientsRequired) {
		this.isSaveRecipientsRequired = isSaveRecipientsRequired;
	}

	public List<RevenuePermission> getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(List<RevenuePermission> revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

}
