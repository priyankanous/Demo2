package com.nous.rollingrevenue.convertor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.vo.AccountVO;
import com.nous.rollingrevenue.vo.LocationVO;

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
			List<Location> locations = new ArrayList<>();
			accountVO.getLocations().stream().forEach(locationVO -> locations.add(LocationConverter.convertLocationVOToLocation(locationVO)));
			account.setLocations(locations);
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
			List<LocationVO> locationVOs = new ArrayList<>();
			account.getLocations().stream().forEach(location -> locationVOs.add(LocationConverter.convertLocationToLocationVO(location)));
			accountVO.setLocations(locationVOs);
			accountVO.setActive(account.isActive());
		}
		return accountVO;
	}

}
