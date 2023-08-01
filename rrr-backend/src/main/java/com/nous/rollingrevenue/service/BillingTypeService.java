package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.BillingTypeVO;

public interface BillingTypeService {

	public void addBillingType(BillingTypeVO billingTypeVO);

	public void updateBillingType( Long id,  BillingTypeVO billingTypeVO);

	public BillingTypeVO getBillingTypeById(Long id);

	public void deleteBillingType( Long id);

	public List<BillingTypeVO> getPagination(int pagenumber, int pagesize, String sortBy);

	public void activateOrDeactivateById(Long id);

}
