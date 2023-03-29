package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.AccountConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.service.AccountService;
import com.nous.rollingrevenue.vo.AccountVO;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<AccountVO> getAllAccounts() {
		List<AccountVO> accountVOs = new ArrayList<>();
		accountRepository.findAll().stream()
				.forEach(account -> accountVOs.add(AccountConverter.convertAccountToAccountVO(account)));
		return accountVOs;
	}

	@Override
	@Transactional
	public AccountVO saveAccount(AccountVO accountVO) {
		Account account = accountRepository.save(AccountConverter.convertAccountVOToAccount(accountVO));
		return AccountConverter.convertAccountToAccountVO(account);
	}

	@Override
	@Transactional
	@CacheEvict(value = "accounts", key= "#accountId")
	public void deleteAccountById(Long accountId) {
		accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException("Account not exist with id:" + accountId));
		accountRepository.deleteById(accountId);
	}

	@Override
	@Cacheable(value = "accounts", key = "#accountId")
	public AccountVO getAccountById(Long accountId) {
		Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RecordNotFoundException("Account not exist with id:" + accountId));
		return AccountConverter.convertAccountToAccountVO(account);
	}

	@Override
	public AccountVO updateAccount(Long accountId, AccountVO accountVO) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException("Account not exist with id:" + accountId));
		account.setAccountName(accountVO.getAccountName());
		account.setAccountOrClientCode(accountVO.getAccountOrClientCode());
		account.setLocation(accountVO.getLocation());
		return AccountConverter.convertAccountToAccountVO(accountRepository.save(account));
		
	}

}
