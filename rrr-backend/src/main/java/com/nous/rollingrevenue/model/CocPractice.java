package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coc_practice")
public class CocPractice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coc_practice_id")
	private Long cocPracticeId;
	
	@Column(name = "coc_practice_name")
	private String cocPracticeName;
	
	@Column(name = "coc_practice_display_name")
	private String cocPracticeDisplayName;
	
	@Column(name = "bu_display_name")
	private String buDisplayName;

	public CocPractice() {

	}

	public CocPractice(Long cocPracticeId, String cocPracticeName, String cocPracticeDisplayName, String buDisplayName) {
		super();
		this.cocPracticeId = cocPracticeId;
		this.cocPracticeName = cocPracticeName;
		this.cocPracticeDisplayName = cocPracticeDisplayName;
		this.buDisplayName = buDisplayName;
	}

	public Long getCocPracticeId() {
		return cocPracticeId;
	}

	public void setCocPracticeId(Long cocPracticeId) {
		this.cocPracticeId = cocPracticeId;
	}

	public String getCocPracticeName() {
		return cocPracticeName;
	}

	public void setCocPracticeName(String cocPracticeName) {
		this.cocPracticeName = cocPracticeName;
	}

	public String getCocPracticeDisplayName() {
		return cocPracticeDisplayName;
	}

	public void setCocPracticeDisplayName(String cocPracticeDisplayName) {
		this.cocPracticeDisplayName = cocPracticeDisplayName;
	}

	public String getBuDisplayName() {
		return buDisplayName;
	}

	public void setBuDisplayName(String buDisplayName) {
		this.buDisplayName = buDisplayName;
	}

	@Override
	public String toString() {
		return "CocPractice [cocPracticeId=" + cocPracticeId + ", cocPracticeName=" + cocPracticeName
				+ ", cocPracticeDisplayName=" + cocPracticeDisplayName + ", buDisplayName=" + buDisplayName + "]";
	}
	

}

