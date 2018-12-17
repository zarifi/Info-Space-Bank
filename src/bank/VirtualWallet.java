package bank;


import java.util.Stack;

import exceptions.InsufficientFundCheckedException;
import exceptions.InsufficientFundException;


public class VirtualWallet {
	
	public TransactionAccount myAccount;
	
	public VirtualWallet(double amount){
		this.myAccount = new TransactionAccount(amount);
	}
	
	public void deposit(TransactionAccount account, double amount){
		myAccount.deposit(account, amount);
		System.out.println("\nUser balance after deposit is: "+"$"+this.getBalance()+"\n");

	}
	
	public void withdraw(TransactionAccount account, double amount){
		try{
			myAccount.withdraw(account, amount);
			System.out.println("\nUser balance after withdraw is: "+"$"+this.getBalance()+"\n");

		}
		catch(InsufficientFundCheckedException error){
			System.out.println("\n"+error.getMessage()+"\n"); 
		}
	}
	
	public double getBalance(){
		return myAccount.getBalance();
	}
	
	public void transfer(TransactionAccount from, TransactionAccount to, double amount){
		try{
			myAccount.transfer(from, to, amount);
		}
		catch(InsufficientFundCheckedException error){
			throw new InsufficientFundException(error.getMessage());
		}
	}
	
	public Stack<String> getHistory(){
		return myAccount.getHistory();
	}

}
