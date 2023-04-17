package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.vo.OpportunityVO;

@Component
public class OpportunityConverter {

	/**
	 * Convert OpportunityVO to Opportunity
	 * 
	 * @param OpportunityVO
	 * @return Opportunity
	 */

	public static Opportunity convertOpportunityVOToOpportunity(OpportunityVO opportunityVO) {
		Opportunity opportunity = new Opportunity();
		if (opportunityVO != null) {
			opportunity.setOpportunityId(opportunityVO.getOpportunityId());
			opportunity.setOpportunityName(opportunityVO.getOpportunityName());
			opportunity.setProjectCode(opportunityVO.getProjectCode());
			opportunity.setProjectStartDate(opportunityVO.getProjectStartDate());
			opportunity.setProjectEndDate(opportunityVO.getProjectEndDate());
			opportunity.setAccount(AccountConverter.convertAccountVOToAccount(opportunityVO.getAccount()));
		}
		return opportunity;
	}

	/**
	 * Convert Opportunity to OpportunityVO
	 * 
	 * @param Opportunity
	 * @return OpportunityVO
	 */

	public static OpportunityVO convertOpportunityToOpportunityVO(Opportunity opportunity) {
		OpportunityVO opportunityVO = new OpportunityVO();
		if (opportunity != null) {
			opportunityVO.setOpportunityId(opportunity.getOpportunityId());
			opportunityVO.setOpportunityName(opportunity.getOpportunityName());
			opportunityVO.setProjectCode(opportunity.getProjectCode());
			opportunityVO.setProjectStartDate(opportunity.getProjectStartDate());
			opportunityVO.setProjectEndDate(opportunity.getProjectEndDate());
			opportunityVO.setAccount(AccountConverter.convertAccountToAccountVO(opportunity.getAccount()));
			opportunityVO.setActive(opportunity.isActive());
		}
		return opportunityVO;
	}

}
