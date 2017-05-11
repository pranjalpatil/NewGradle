package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}


	@Override
	public int depositAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException {
		// TODO Auto-generated method stub
		Account account=accountRepository.searchAccount(accountNumber);
		if(account != null){
		if(account.getAccountNumber() == accountNumber){
			account.setAmount(account.getAmount()+amount);
			System.out.println("Amount in deposit "+account.getAmount());
			return account.getAmount();
		}
		}
		throw new InvalidAccountNumberException();

	}


	@Override
	public int WithdrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		// TODO Auto-generated method stub
		Account account=accountRepository.searchAccount(accountNumber);
		if(account != null){
			
			if((account.getAmount()< amount)){
				throw new InsufficientBalanceException();
			}
		if((account.getAccountNumber() == accountNumber))
		{
			account.setAmount(account.getAmount()-amount);
			System.out.println("Amount in withdrawl "+account.getAmount());
			return account.getAmount();
		}
		
		}
		
		throw new InvalidAccountNumberException();
	}

}
