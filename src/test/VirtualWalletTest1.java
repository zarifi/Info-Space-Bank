package test;

import static org.junit.Assert.*;

import java.util.Stack;

import bank.TransactionAccount;
import bank.VirtualWallet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VirtualWalletTest1 {

	VirtualWallet testWallet;
	@Before
	public void setUp() throws Exception {
		testWallet = new VirtualWallet(100);
	}

	@Test
	public void testDeposit() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.deposit(currentAccount,150);
		double currentBalance = testWallet.getBalance(currentAccount);
		assertEquals(250, currentBalance,0.1);
	}

	@Test
	public void testWithdraw() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		try{
			testWallet.withdraw(currentAccount,10);	
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		assertEquals(90,currentAccount.getBalance(),0.1);
	}

	@Test
	public void testGetBalance() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		assertEquals(100,currentAccount.getBalance(),0.1);
	}

	@Test
	public void testTransfer() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		VirtualWallet testWallet2 = new VirtualWallet(0);
		testWallet.transfer(currentAccount, testWallet2.getAccount(1),30);
		assertEquals(70, currentAccount.getBalance(),0.1);
		assertEquals(30,testWallet2.getAccount(1).getBalance(),0.1);
	}

	@Test
	public void testGetHistory() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.deposit(currentAccount, 30);
		testWallet.withdraw(currentAccount, 10);
		testWallet.deposit(currentAccount, 10);
		Stack<String> testList = testWallet.getHistory(currentAccount);
		
		String firstTransaction = testList.elementAt(0);
		System.out.println(firstTransaction);
		Assert.assertTrue(firstTransaction.contains("Type: Deposit  Amount: $30"));
		
		String secondTransaction = testList.elementAt(1);
		Assert.assertTrue(secondTransaction.contains("Type: Withdraw  Amount: $10"));
		
		String thirdTransaction = testList.elementAt(2);
		Assert.assertTrue(thirdTransaction.contains("Type: Deposit  Amount: $10"));
		
	}

}
