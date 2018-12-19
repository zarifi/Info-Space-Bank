package test;

import static org.junit.Assert.*;

import java.util.Stack;

import bank.TransactionAccount;
import bank.VirtualWallet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VirtualWalletTest2 {

	VirtualWallet testWallet;
	@Before
	public void setUp() throws Exception {
		testWallet = new VirtualWallet(350);
	}

	@Test
	public void testDeposit() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.deposit(currentAccount, 100);
		double currentBalance = testWallet.getBalance(currentAccount);
		assertEquals(450, currentBalance,0.1);
	}

	@Test
	public void testWithdraw() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.withdraw(currentAccount, 20);
		assertEquals(330,testWallet.getBalance(currentAccount),0.1);
	}

	@Test
	public void testGetBalance() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		assertEquals(350,testWallet.getBalance(currentAccount),0.1);
	}

	@Test
	public void testTransfer() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		VirtualWallet testWallet2 = new VirtualWallet(0);
		testWallet.transfer(currentAccount, testWallet2.getAccount(1),100);
		assertEquals(250, testWallet.getBalance(currentAccount),0.1);
		assertEquals(100,testWallet2.getBalance(testWallet2.getAccount(1)),0.1);
	}

	@Test
	public void testGetHistory() {
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.deposit(currentAccount, 10);
		testWallet.withdraw(currentAccount, 20);
		testWallet.deposit(currentAccount, 30);
		Stack<String> testList = testWallet.getHistory(currentAccount);
		
		String firstTransaction = testList.elementAt(0);
		System.out.println(firstTransaction);
		Assert.assertTrue(firstTransaction.contains("Type: Deposit  Amount: $10"));
		
		String secondTransaction = testList.elementAt(1);
		Assert.assertTrue(secondTransaction.contains("Type: Withdraw  Amount: $20"));
		
		String thirdTransaction = testList.elementAt(2);
		Assert.assertTrue(thirdTransaction.contains("Type: Deposit  Amount: $30"));
		
	}

}
