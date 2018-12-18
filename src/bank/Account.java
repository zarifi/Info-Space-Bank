package bank;


/**
 * The abstract class Account which can be used as a super class to all types of accounts (Transaction Account, Saving Account, Checking Account)
 */

import java.util.Stack;

import exceptions.InsufficientFundCheckedException;
import exceptions.InsufficientFundException;
import exceptions.NullUserCheckedException;
import exceptions.NullUserException;


public abstract class Account {
	public double balance;
	
	public void deposit(Account account, double amount){}
	
	public void withdraw(Account account, double amount) throws InsufficientFundCheckedException
	{}
	
	public double getBalance(){
		return this.balance;
	}
	
	//public void setBalance(double account){}
	
	public void transfer(Account from, Account to, double amount) throws InsufficientFundCheckedException, NullUserCheckedException {}
	
	public Stack<String> getHistory(Account account){
		Stack<String> st = new Stack<String>();
		return st;
	}

}
