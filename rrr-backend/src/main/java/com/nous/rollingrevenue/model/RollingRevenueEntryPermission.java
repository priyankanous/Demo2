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
@Table(name = "rolling_revenue_entry_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RollingRevenueEntryPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rolling_revenue_entry_permission_id")
	private Long rollingrevenueEntryPermissionId;

	@Column(name = "rolling_revenue_entry_permission_all")
	private boolean isRollingRevenueEntryPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@Column(name = "add_revenue_entry")
	private boolean isAddRevenueEntryRequired;

	@Column(name = "edit_revenue_entry")
	private boolean isEditRevenueEntryRequired;

	@Column(name = "delete_revenue_entry")
	private boolean isDeleteRevenueEntryRequired;

	@Column(name = "copy_revenue_entry")
	private boolean isCopyRevenueEntryRequired;

	@Column(name = "submit_revenue_entry")
	private boolean isSubmitRevenueEntryRequired;

	@Column(name = "export")
	private boolean isExportRequired;

	@OneToMany(mappedBy = "rollingRevenueEntryPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

}
