package bank;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exceptions.InsufficientFundCheckedException;
import exceptions.InsufficientFundException;
import exceptions.NullUserCheckedException;
import exceptions.NullUserException;


public class VirtualWallet {
	
	//list of all TransactionAccounts;
	public List<TransactionAccount> accounts; 
	
	public VirtualWallet(double initialBalance){
		accounts = new ArrayList<TransactionAccount>();
		accounts.add(new TransactionAccount(initialBalance));
		//this.myAccount = new TransactionAccount(initialBalance);
	}
	
	// add a new Transaction Account to current Virtual Wallet
	public void addAccount(double initialBalance){
		accounts.add(new TransactionAccount(initialBalance));
	}
	
	//deposit the amount to selected account
	public void deposit(TransactionAccount account, double amount){
		account.deposit(amount);
		System.out.println("\nUser balance after deposit is: "+"$"+account.getBalance()+"\n");

	}
	// Withdraw money from account, checks for $0 withdraw and non sufficient funcd exception
	public void withdraw(TransactionAccount account, double amount){
		try{
			account.withdraw(amount);
			System.out.println("\nUser balance after withdraw is: "+"$"+account.getBalance()+"\n");

		}
		catch(InsufficientFundCheckedException error){
			//System.out.println("\n"+error.getMessage()+"\n"); 
			throw new InsufficientFundException("\n"+error.getMessage()+"\n");
		}
	}
	
	// return the balance of the account
	public double getBalance(TransactionAccount account){
		return account.getBalance();
	}
	// Transfer money from one account to another and checks for any transaction violation (exceeding withdraw,...)
	public void transfer(TransactionAccount from,TransactionAccount to, double amount){
		try{
			from.transfer(to, amount);
		}
		catch(InsufficientFundCheckedException error){
			throw new InsufficientFundException("\n"+error.getMessage()+"\n");
		}
		catch(NullUserCheckedException error){
				throw new NullUserException("\n"+error.getMessage()+"\n");
			
		}
	}
	
	// Return the history transaction in forms of Stack
	public Stack<String> getHistory(TransactionAccount account){
		return account.getHistory();
	}
	
	// Returns the Transaction Account provided its number
	public TransactionAccount getAccount(int accountNumber){
		return accounts.get(accountNumber-1);
	}
}
