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
		System.out.println("####### Welcom to Infinity Space Bank #######\n\n");
		

		do {
			System.out.println("\n## Select among the following options:## \n");
			System.out.println("	1. Create a Transaction Account for user");
			System.out.println("	2. List all clients (users)");
			System.out.println("	3. Select User");
			System.out.println("	0. Exit");
			
			selection = in.nextInt();
			
			switch(selection){
				// When a user is created the corresponding Virtual Wallet is also created
				// The user name of each user should be unique
				case 1:
					System.out.println("\nEnter the name of the user and then enter\n");
					String userName = in.next();
					if (clientList.containsKey(userName))
						System.out.println("\nUser "+userName+" already exists.\n");
					else {
						System.out.println("\nEnter the amount of money to be deposited:\n");
						double firstDeposit = in.nextDouble();
						clientList.put(userName, new Client(userName,firstDeposit));
					}
					break;
					
					
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
					
				case 3:
					System.out.println("\nType a user name\n");
					String selectUser = in.next();
					if (!clientList.containsKey(selectUser))
						System.out.println("\nUser "+ selectUser+" does not exist. Try again!\n");
					else{
						boolean backToMainMenu = false;
						Client user = clientList.get(selectUser);

						do {
							System.out.println("	1. Deposit");
							System.out.println("	2. Withdraw");
							System.out.println("	3. Transfer");
							System.out.println("	4. Get Balance");
							System.out.println("	5. Transaction History");
							System.out.println("	0. Go back to Main Menu");
							
							int userMenuOption = in.nextInt();
							
							switch(userMenuOption){
							
								case 1:
									System.out.println("\nEnter the amount to be deposited (and enter):\n");
									double amountDeposit = in.nextDouble();
									user.myWallet.deposit(user.myWallet.myAccount,amountDeposit);
									//System.out.println("$"+user.myWallet.getBalance());
									break;
									
								case 2:
									System.out.println("\nEnter the amount to be withdrawn (and enter):\n");
									double amountWithdraw = in.nextDouble();
									try{
										user.myWallet.withdraw(user.myWallet.myAccount, amountWithdraw);
									}
									catch(Exception e){
										System.out.println(e.getMessage());
									}
									//System.out.println("\nUser "+selectUser+" balance after withdraw is: "+"$"+user.myWallet.getBalance()+"\n");
									//System.out.println("$"+user.myWallet.getBalance());
									break;
									
								case 3:
									System.out.println("\nEnter the user name that you want to transfer money to its account (and enter):\n");
									String toUserName = in.next();
									Client toUser = clientList.get(toUserName);
									if (toUser == null)
									{
										System.out.println("User "+toUserName+" does not exist!");
									}
									else
									{
										System.out.println("\nEnter the amount to be transferred (and enter):\n");
										double amountTransfer = in.nextDouble();
										try
										{
											user.myWallet.transfer(user.myWallet.myAccount, null, amountTransfer);
											System.out.println("\nUser "+selectUser+" balance after transfer is: "+"$"+user.myWallet.getBalance()+"\n");
										}
										catch(Exception e) {
											System.out.println(e.getMessage());
										}
										//System.out.println("$"+user.myWallet.getBalance());
									}							
									break;
									
								case 4:
									System.out.println("\nCurrent balacne is: "+"$"+user.myWallet.getBalance()+"\n");
									break;
									
								case 5:
									System.out.println("\nList of past 100 latest transactions:\n\n");
									Stack<String> list = user.myWallet.getHistory();
									int j  = 1;
									for (int i = list.size() - 1;i >= 0 ;i--){
										System.out.println(j+") "+list.elementAt(i));
										j++;
									}
									System.out.println("");
									break;
								
								
								case 0:
									backToMainMenu = true;
									break;
									
								default:
									System.out.println("\nInvalid entry. Please try again!\n");
									break;	
							}	
						}
						while (!backToMainMenu);
					}
					break;
					
				
				case 0:
					exit = true;
					break;
					
				default:
					System.out.println("\nInvalid entry. Please try again!\n");
					break;
					
			}
			
		} while (!exit);

	}

}
