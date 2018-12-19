package bank;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class MainBank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Client> clientList = new HashMap<String, Client>();
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		int selection;
		System.out.println("\n\n####### Welcom to Infinity Space Bank #######\n\n");
		
		// Main option of the program
		do {
			System.out.println("\n## Select among the following options:## \n");
			System.out.println("	1. Create a Virtual Wallet for user");
			System.out.println("	2. List all clients (users)");
			System.out.println("	3. Select User");
			System.out.println("	0. Exit");
			
			selection = in.nextInt();
			
			switch(selection){
				// When a user is created the corresponding Virtual Wallet is also created
				// The user name of each user should be unique
				case 1:
					System.out.println("\nEnter the name of the user\n");
					String userName = in.next();
					if (clientList.containsKey(userName))
						System.out.println("\nUser "+userName+" already exists.\n");
					else {
						System.out.println("\nEnter the amount of money to be deposited:\n");
						double firstDeposit = in.nextDouble();
						clientList.put(userName, new Client(userName,firstDeposit));
					}
					break;
					
				// List all existing users in the bank	
				case 2:
					if (clientList.isEmpty())	
						System.out.println("\nThere is no client in the Bank\n");
					else
					{
						System.out.println("\nList of current users:\n");
						Iterator it = clientList.entrySet().iterator();
						int i = 1;
						while (it.hasNext()){
							Map.Entry pair = (Map.Entry)it.next();
							System.out.println(i+") "+pair.getKey());
							System.out.println("");
							i++;
						}
					}
					break;
					
				// Select user in order to perform transaction on his/her accounts	
				case 3:
					System.out.println("\nType a user name (and enter)\n");
					String selectUser = in.next();
					if (!clientList.containsKey(selectUser))
						System.out.println("\nUser "+ selectUser+" does not exist. Try again!\n");
					else{
						boolean backToMainMenu = false;
						Client user = clientList.get(selectUser);
						
						// Account number should be selected by user
						System.out.println("\nEnter the account number:\n");
						int accountNumber = in.nextInt();
						TransactionAccount currentAccount;
						try {
							currentAccount = user.myWallet.getAccount(accountNumber);
						}
						catch(Exception e){
							System.out.println("\nAccount with number "+accountNumber+" does not exist.\n");
							System.out.println("\nFirst user account has been selected. You can try switch account"
									+ " by entering number 6 from the options.\n");
							currentAccount = user.myWallet.getAccount(1);
						}
						
						// User options 
						do {
							System.out.println("	1. Deposit");
							System.out.println("	2. Withdraw");
							System.out.println("	3. Transfer");
							System.out.println("	4. Get Balance");
							System.out.println("	5. Transaction History");
							System.out.println("	6. Switch Account");
							System.out.println("	7. Add Transaction Account");
							System.out.println("	8. List All Accounts Balance");
							System.out.println("	0. Go back to Main Menu");
							
							int userMenuOption = in.nextInt();
							
							switch(userMenuOption){
							
								case 1:
									System.out.println("\nEnter the amount to be deposited:\n");
									double amountDeposit = in.nextDouble();
									user.myWallet.deposit(currentAccount,amountDeposit);
									//System.out.println("$"+user.myWallet.getBalance());
									break;
									
								case 2:
									System.out.println("\nEnter the amount to be withdrawn:\n");
									double amountWithdraw = in.nextDouble();
									try{
										user.myWallet.withdraw(currentAccount, amountWithdraw);
									}
									catch(Exception e){
										System.out.println(e.getMessage());
									}
									//System.out.println("\nUser "+selectUser+" balance after withdraw is: "+"$"+user.myWallet.getBalance()+"\n");
									//System.out.println("$"+user.myWallet.getBalance());
									break;
									
								case 3:
									System.out.println("\nEnter the user name that you want to transfer money to its account:\n");
									String toUserName = in.next();
									Client toUser = clientList.get(toUserName);
									if (toUser == null)
									{
										System.out.println("\nUser "+toUserName+" does not exist! Enter an existing username.\n");
									}
									else
									{
										System.out.println("\nEnter the account number of recipient:\n");
										int accNumber = in.nextInt();
										TransactionAccount toAccount;
										try {
											toAccount = toUser.myWallet.getAccount(accNumber);
										}
										catch(Exception e){
											System.out.println("\nAccount with number "+accNumber+" does not exist.\n");
											System.out.println("\nPlease try again\n");
											break;
										}
										
										System.out.println("\nEnter the amount to be transferred:\n");
										double amountTransfer = in.nextDouble();
										try
										{
											user.myWallet.transfer(currentAccount, toUser.myWallet.getAccount(accNumber), amountTransfer);
											System.out.println("\nUser "+selectUser+" balance for current account after transfer is: "+"$"+currentAccount.getBalance()+"\n");
										}
										catch(Exception e) {
											System.out.println(e.getMessage());
										}
										//System.out.println("$"+user.myWallet.getBalance());
									}							
									break;
									
								case 4:
									System.out.println("\nCurrent balacne is: "+"$"+currentAccount.getBalance()+"\n");
									break;
								// Display latest 100 transactions in order of their timing	
								case 5:
									System.out.println("\nList of past 100 latest transactions:\n\n");
									Stack<String> list = currentAccount.getHistory();
									int j  = 1;
									for (int i = list.size() - 1;i >= 0 ;i--){
										System.out.println(j+") "+list.elementAt(i));
										j++;
									}
									System.out.println("");
									break;
								// User can change account using this option	
								case 6: 
									System.out.println("\nEnter the account number: \n");
									int requestAccountNumber = in.nextInt(); 
									
									try{
										currentAccount = user.myWallet.getAccount(requestAccountNumber);
									}
									// default account (first account) is selected if the entered account # is incorrect
									catch(Exception e){
										System.out.println("\nAccount with number "+requestAccountNumber+" does not exist.\n");
										System.out.println("\nFirst user account has been selected. You can try switch account"
												+ "by entering number 6 from the options.\n");
										int range = user.myWallet.accounts.size();
										System.out.println("\nExisting account numbers for current user are "
												+ "1.."+range+" \n");
										currentAccount = user.myWallet.getAccount(1);
									}									
									break;
								// This option is used to create a new Transaction account for current user	
								case 7:
									System.out.println("\nEnter initial deposit for your new account: \n");
									double newInitialDeposit = in.nextDouble();
									user.myWallet.addAccount(newInitialDeposit);
									break;
								// displays a list of all accounts and their balance that user currently own	
								case 8:
									for (int i = 0;i < user.myWallet.accounts.size();i++){
										double currBalance = user.myWallet.getAccount(i+1).getBalance();
										System.out.println("\nAccount # "+(i+1)+" Balance: "+currBalance+"\n");
									}
									System.out.println("");
									break;
								
								case 0:
									backToMainMenu = true;
									break;
								// wrong input would prompt user to try again.
								default:
									System.out.println("\nInvalid entry. Please try again!\n");
									break;	
							}	
						} // end of User menu
						while (!backToMainMenu);
					}
					break;
					
				
				case 0:
					exit = true;
					break;
					
				default:
					System.out.println("\nInvalid entry. Please try again!\n");
					break;
					
			} // end of program main menu
		
		} while (!exit);

	}

}
