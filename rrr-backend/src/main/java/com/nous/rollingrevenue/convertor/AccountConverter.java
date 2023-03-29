package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.vo.AccountVO;

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
			if (accountVO.getAccountId() != null) {
				account.setAccountId(accountVO.getAccountId());
			}
			account.setAccountName(accountVO.getAccountName());
			account.setAccountOrClientCode(accountVO.getAccountOrClientCode());
			account.setLocation(accountVO.getLocation());
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
			accountVO.setLocation(account.getLocation());
		}
		return accountVO;
	}


}
