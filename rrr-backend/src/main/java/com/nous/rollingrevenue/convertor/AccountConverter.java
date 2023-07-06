package com.nous.rollingrevenue.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.vo.AccountVO;
import com.nous.rollingrevenue.vo.RegionVO;

@Component
public class AccountConverter {

	/**
	 * Convert AccountVO to Account
	 * 
	 * @param AccountVO
	 * @return Account
	 */

	public static Account convertAccountVOToAccount(AccountVO accountVO) {
		Account account = new Account();
		if (accountVO != null) {
			account.setAccountId(accountVO.getAccountId());
			account.setAccountName(accountVO.getAccountName());
			account.setAccountOrClientCode(accountVO.getAccountOrClientCode());
			List<Region> regions = new ArrayList<>();
			accountVO.getRegions().stream()
					.forEach(regionVO -> regions.add(RegionConverter.convertRegionVOToRegion(regionVO)));
			account.setRegions(regions);
		}
		return account;
	}

	/**
	 * Convert Account to AccountVO
	 * 
	 * @param Account
	 * @return AccountVO
	 */

	public static AccountVO convertAccountToAccountVO(Account account) {
		AccountVO accountVO = new AccountVO();
		if (account != null) {
			accountVO.setAccountId(account.getAccountId());
			accountVO.setAccountName(account.getAccountName());
			accountVO.setAccountOrClientCode(account.getAccountOrClientCode());
			List<RegionVO> regionVOs = new ArrayList<>();
			account.getRegions().stream()
					.forEach(region -> regionVOs.add(RegionConverter.convertRegionToRegionVO(region)));
			accountVO.setRegions(regionVOs);
			accountVO.setActive(account.isActive());
		}
		return accountVO;
	}

}
