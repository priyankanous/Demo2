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
import lombok.Data;

@Entity
@Table(name = "ReviewAndPublishPermission")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
