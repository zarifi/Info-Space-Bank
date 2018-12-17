package bank;


import java.sql.Timestamp;
import java.util.Stack;

import exceptions.InsufficientFundCheckedException;
import exceptions.InsufficientFundException;
import exceptions.NullUserException;


public class TransactionAccount extends Account {
	
	private final int N = 100;
	private Timestamp time;
	private double balance;
	private Stack<TransactionData> historyList;
	
	// This class is used store information for each transaction to be displayed later on upon requesting for transaction history
	private class TransactionData{
		private Timestamp time;
		private String type;
		private double amount;
		// For each transaction we store its time, its type (deposit, withdraw, transfer) and the amount
		private TransactionData(Timestamp timeValue,String typeValue,double amountValue){
			time = timeValue;
			type = typeValue;
			amount = amountValue;
		}
	}
	
	public TransactionAccount(double amount){
		this.balance = amount;
		this.time = new Timestamp(System.currentTimeMillis());
		this.historyList = new Stack<TransactionData>();
	}
	
	// Deposit "amount" to account. It stores the transaction information in transaction history list 
	public void deposit(TransactionAccount account, double amount){
		double currentBalance = account.getBalance();
		setBalance(account,amount + currentBalance);
		//System.out.println(amount + currentBalance);
		this.time = new Timestamp(System.currentTimeMillis());
		addToHistoryList(this.time, "Deposit", amount);	
	}
	
	// Withdraw "amount" from account. This method prevents any withdraw if withdraw results into $0 balance or negative balance
	public void withdraw(TransactionAccount account, double amount) throws InsufficientFundCheckedException{
		double currentBalance = account.getBalance();
		if (currentBalance <= amount)
		{
			throw new InsufficientFundCheckedException("Current balance is less than requested amount to withdraw");
		}
		else
		{
			setBalance(account, currentBalance - amount);
			//System.out.println(currentBalance);
			//System.out.println(currentBalance - amount);
			this.time = new Timestamp(System.currentTimeMillis());
			addToHistoryList(this.time, "Withdraw", amount);
		}
			
	}
	
	// Return the balance of the current account
	public double getBalance(){
		return this.balance;
	}
	
	//
	public void transfer(TransactionAccount from, TransactionAccount to, double amount) throws InsufficientFundCheckedException{
		double currentBalanceFrom = from.getBalance();
		double currentBalanceTo = to.getBalance();
		if (currentBalanceFrom <= amount){
			throw new InsufficientFundCheckedException("Current balance is less than requested amount to tarnsfer");
		}
		else
		{			
			setBalance(from,currentBalanceFrom - amount);
			setBalance(to,currentBalanceTo + amount);
			//System.out.println(from.getBalance());
			this.time = new Timestamp(System.currentTimeMillis());
			addToHistoryList(this.time, "Transfer", amount);		
		}
	}
	
	public Stack<String> getHistory(){
		//System.out.println(this.historyList.size());
		Stack<String> list = new Stack<String>();
		for (int i = 0;i < this.historyList.size();i++){
			TransactionData current = this.historyList.elementAt(i);
			System.out.println(current.amount);
			//System.out.println("Time: "+current.time + "  Type: "+ current.type+ "  Amount: "+current.amount);
			list.push("Time: "+current.time + "  Type: "+ current.type+ "  Amount: $"+current.amount);
		}
		return list;
	}
	
	// This method is used as a helper method for other transaction methods and it set balance of an account
	private void setBalance(TransactionAccount account, double amount){
		account.balance = amount;
	}
	
	// Helper method to store transaction information to the history list
	private void addToHistoryList(Timestamp time, String type, double amount){
		if (this.historyList.size() == N){
			this.historyList.removeElementAt(N-1);
		}
	
		TransactionData toBeAdded = new TransactionData(time, type,amount);
		this.historyList.push(toBeAdded);
		
	}
	
}
