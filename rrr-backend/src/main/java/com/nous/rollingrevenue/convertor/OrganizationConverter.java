package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Organization;
import com.nous.rollingrevenue.vo.OrganizationVO;

/**
 * @author Nous Infosystems
 *
 */
@Component
public class OrganizationConverter {
	/**
	 * Convert OrganizationVO to Organization
	 * 
	 * @param OrganizationVO
	 * @return organization Jpa bean
	 */
	public static Organization convertOrganizationVOToOrganization(OrganizationVO organizationVO) {
		Organization organization = new Organization();
		organization.setId(organizationVO.getId());
		organization.setOrgName((organizationVO.getOrgName()));
		organization.setOrgDisplayName(organizationVO.getOrgDisplayName());
	return organization;

	}

	/**
	 * Convert Organization to OrganizationVO
	 * 
	 * @param Organization
	 * @return organizationVO
	 */
	public static OrganizationVO convertOrganizationToOrganizationVO(Organization organization) {
		OrganizationVO organizationVO = new OrganizationVO();
		if (organization != null) {
			organizationVO.setId(organization.getId());
			organizationVO.setOrgName(organization.getOrgName());
			organizationVO.setOrgDisplayName((organization.getOrgDisplayName()));
			organizationVO.setActive(organization.isActive());
		}

		return organizationVO;

	}

}
