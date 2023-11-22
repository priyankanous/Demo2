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
@Table(name = "coc_practise_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class COCPracticePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coc_practice_permission_id")
	private Long cocPracticePermissionId;

	@Column(name = "coc_practice_permission_all")
	private boolean isCocPracticePermissionAll;

	@OneToMany(mappedBy = "cocPracticePermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

}
