package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.vo.RevenueEntryVO;

@Component
public class RevenueEntryConverter {

	private RevenueEntryConverter() {
		super();
	}

	public static RevenueEntry convertRevenueEntryVOToRevenueEntry(RevenueEntryVO revenueEntryVO) {
		RevenueEntry revenueEntry = new RevenueEntry();
		if (revenueEntryVO != null) {
			revenueEntry.setRevenueEntryId(revenueEntryVO.getRevenueEntryId());
		}
		return revenueEntry;
	}

	public static RevenueEntryVO convertRevenueEntryToRevenueEntryVO(RevenueEntry revenueEntry) {
		RevenueEntryVO revenueEntryVO = new RevenueEntryVO();
		if (revenueEntry != null) {
			revenueEntryVO.setRevenueEntryId(revenueEntry.getRevenueEntryId());
		}
		return revenueEntryVO;
	}

}