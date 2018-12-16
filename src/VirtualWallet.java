
public class VirtualWallet {
	
	public TransactionAccount myAccount;
	
	public VirtualWallet(){
		this.myAccount = new TransactionAccount();
	}
	
	public void deposit(Account account, double amount){
		myAccount.deposit(account, amount);
	}
	
	public void withdraw(Account account, double amount){
		myAccount.withdraw(account, amount);
	}
	
	public double getBalance(){
		return myAccount.getBalance();
	}
	
	public void transfer(Account from, Account to, double amount){
		myAccount.transfer(from, to, amount);
	}
	
	public void getHistory(Account account){
		myAccount.getHistory(account);
	}

}
