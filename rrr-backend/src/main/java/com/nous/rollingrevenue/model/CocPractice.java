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
@Table(name = "coc_practice")
@EntityListeners(AuditingEntityListener.class)
public class CocPractice extends Auditable<String> {

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

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public CocPractice() {

	}

	public CocPractice(Long cocPracticeId, String cocPracticeName, String cocPracticeDisplayName, String buDisplayName,
			boolean isActive) {
		super();
		this.cocPracticeId = cocPracticeId;
		this.cocPracticeName = cocPracticeName;
		this.cocPracticeDisplayName = cocPracticeDisplayName;
		this.buDisplayName = buDisplayName;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CocPractice [cocPracticeId=" + cocPracticeId + ", cocPracticeName=" + cocPracticeName
				+ ", cocPracticeDisplayName=" + cocPracticeDisplayName + ", buDisplayName=" + buDisplayName
				+ ", isActive=" + isActive + "]";
	}

}
