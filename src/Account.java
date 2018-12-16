
public abstract class Account {
	public double balance;
	
	public void deposit(Account account, double amount){}
	
	public void withdraw(Account account, double amount){}
	
	public double getBalance(){
		return this.balance;
	}
	
	public void setBalance(double account){}
	
	public void transfer(Account from, Account to, double amount){}
	
	public void getHistory(Account account){}

}
