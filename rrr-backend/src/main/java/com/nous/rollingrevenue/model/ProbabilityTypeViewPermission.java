package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "probability_type_view_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ProbabilityTypeViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "probability_type_view_permission_id")
	private Long probabilityTypeViewPermissionId;

	@Column(name = "probability_type_view_permission_all")
	private boolean isProbabilityTypeViewPermissionAll;

	@OneToMany(mappedBy = "probabilityTypeViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

}
