package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.nous.rollingrevenue.model.converter.StringSetToStringConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buiness_development_manager")
@EntityListeners(AuditingEntityListener.class)
public class BusinessDevelopmentManager extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_id")
	private Long bdmId;

	@Column(name = "bdm_name")
	private String bdmName;

	@Column(name = "bdm_display_name")
	private String bdmDisplayName;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@Column(name = "linked_to_bu")
	@Convert(converter = StringSetToStringConverter.class)
	private Set<String> linkedToBusinessUnit = new HashSet<>();

	@Column(name = "linked_to_region")
	@Convert(converter = StringSetToStringConverter.class)
	private Set<String> linkedToRegion = new HashSet<>();

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public BusinessDevelopmentManager() {

	}

	public BusinessDevelopmentManager(Long bdmId, String bdmName, String bdmDisplayName, LocalDate activeFrom,
			LocalDate activeUntil, Set<String> linkedToBusinessUnit, Set<String> linkedToRegion, boolean isActive) {
		super();
		this.bdmId = bdmId;
		this.bdmName = bdmName;
		this.bdmDisplayName = bdmDisplayName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.linkedToBusinessUnit = linkedToBusinessUnit;
		this.linkedToRegion = linkedToRegion;
		this.isActive = isActive;
	}

	public Long getBdmId() {
		return bdmId;
	}

	public void setBdmId(Long bdmId) {
		this.bdmId = bdmId;
	}

	public String getBdmName() {
		return bdmName;
	}

	public void setBdmName(String bdmName) {
		this.bdmName = bdmName;
	}

	public String getBdmDisplayName() {
		return bdmDisplayName;
	}

	public void setBdmDisplayName(String bdmDisplayName) {
		this.bdmDisplayName = bdmDisplayName;
	}

	public LocalDate getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(LocalDate activeFrom) {
		this.activeFrom = activeFrom;
	}

	public LocalDate getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(LocalDate activeUntil) {
		this.activeUntil = activeUntil;
	}

	public Set<String> getLinkedToBusinessUnit() {
		return linkedToBusinessUnit;
	}

	public void setLinkedToBusinessUnit(Set<String> linkedToBusinessUnit) {
		this.linkedToBusinessUnit = linkedToBusinessUnit;
	}

	public Set<String> getLinkedToRegion() {
		return linkedToRegion;
	}

	public void setLinkedToRegion(Set<String> linkedToRegion) {
		this.linkedToRegion = linkedToRegion;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BusinessDevelopmentManager [bdmId=" + bdmId + ", bdmName=" + bdmName + ", bdmDisplayName="
				+ bdmDisplayName + ", activeFrom=" + activeFrom + ", activeUntil=" + activeUntil
				+ ", linkedToBusinessUnit=" + linkedToBusinessUnit + ", linkedToRegion=" + linkedToRegion
				+ ", isActive=" + isActive + "]";
	}

}
