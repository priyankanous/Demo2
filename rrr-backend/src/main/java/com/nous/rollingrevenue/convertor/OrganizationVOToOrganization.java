package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Organization;
import com.nous.rollingrevenue.vo.OrganizationVO;

/**
 * @author Nous Infosystems
 *
 */
@Component
public class OrganizationVOToOrganization {
	/**
	 * Convert OrganizationVO to Organization
	 * 
	 * @param OrganizationVO
	 * @return organization Jpa bean
	 */
	public static Organization convertOrganizationVOToOrganization(OrganizationVO organizationVO) {
		Organization organization = new Organization();
		if (organizationVO != null) {
			if (organizationVO.getId() != null) {
				organization.setId(organizationVO.getId());
			}
			organization.setId(organizationVO.getId());
			organization.setorgName((organizationVO.getorgName()));
			organization.setorgDisplayName(organizationVO.getorgDisplayName());
		}

		return organization;

	}

	/**
	 * Convert Organization to OrganizationVO
	 * 
	 * @param Organization organization
	 * @return organizationVO
	 */
	public static OrganizationVO convertOrganizationToOrganizationVO(Organization organization) {
		OrganizationVO organizationVO = new OrganizationVO();
		if (organization != null) {
			organizationVO.setId(organization.getId());
			organizationVO.setorgName(organization.getorgName());
			organizationVO.setorgDisplayName((organization.getorgDisplayName()));
		}

		return organizationVO;

	}

}
