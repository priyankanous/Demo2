package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "region")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Region extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Long regionId;

	@Column(name = "region_name")
	private String regionName;

	@Column(name = "region_display_name")
	private String regionDisplayName;

	@OneToMany(mappedBy = "regions")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<>();

	@ManyToMany(mappedBy = "regions")
	@JsonManagedReference
	private List<BusinessDevelopmentManager> businessDevlopmentManagers = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

}
