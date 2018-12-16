import java.sql.Timestamp;
import java.util.Stack;


public class TransactionAccount extends Account {
	
	private final int N = 100;
	private Timestamp time;
	private double balance;
	private Stack<TransactionData> historyList;
	
	private class TransactionData{
		private Timestamp time;
		private String type;
		private double amount;
		private TransactionData(Timestamp timeValue,String typeValue,double amountValue){
			time = timeValue;
			type = typeValue;
			amount = amountValue;
		}
	}
	
	public TransactionAccount(){
		this.balance = 0;
		this.time = new Timestamp(System.currentTimeMillis());
		this.historyList = new Stack<TransactionData>();
	}
	
	public void deposit(Account account, double amount){
		double currentBalance = account.getBalance();
		account.setBalance(amount + currentBalance);
		//System.out.println(currentBalance);
		this.time = new Timestamp(System.currentTimeMillis());
		addToHistoryList(this.time, "Deposit", amount);	
	}
	
	public void withdraw(Account account, double amount){
		double currentBalance = account.getBalance();
		account.setBalance(currentBalance - amount);
		//System.out.println(currentBalance);
		//System.out.println(currentBalance - amount);
		this.time = new Timestamp(System.currentTimeMillis());
		addToHistoryList(this.time, "Withdraw", amount);	
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public void transfer(Account from, Account to, double amount){
		double currentBalanceFrom = from.getBalance();
		double currentBalanceTo = to.getBalance();
		from.setBalance(currentBalanceFrom - amount);
		to.setBalance(currentBalanceTo + amount);
		//System.out.println(from.getBalance());
		this.time = new Timestamp(System.currentTimeMillis());
		addToHistoryList(this.time, "Transfer", amount);	
	}
	
	public void getHistory(Account account){
		//System.out.println(this.historyList.size());
		for (int i = 0;i < this.historyList.size();i++){
			TransactionData current = this.historyList.elementAt(i);
			//System.out.println(i);
			System.out.println("Time: "+current.time + "  Type: "+ current.type+ "  Amount: "+current.amount);
		}
	}
	
	public void setBalance(double amount){
		this.balance = amount;
	}
	
	private void addToHistoryList(Timestamp time, String type, double amount){
		if (this.historyList.size() == N)
			this.historyList.pop();
		else
		{
			TransactionData toBeAdded = new TransactionData(time, type,amount);
			this.historyList.push(toBeAdded);
		}
	}
	
}
