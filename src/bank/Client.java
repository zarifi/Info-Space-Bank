package bank;

/**
 * 
 * @author Mohammad
 * Client is the class to represent each individual user. Client has a VirtualWallet componenet which is used
 * as an interface to make transaction. Each client (user) has a user name which we assume it's unique in entire
 * bank.
 *
 */

public class Client {
	
	public VirtualWallet myWallet;
	public String userName;
	
	public Client(String Name, double amount){
		myWallet = new VirtualWallet(amount);
		this.userName = Name;
	}
	
}
