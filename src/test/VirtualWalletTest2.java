package test;

import static org.junit.Assert.*;

import java.util.Stack;

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
		testWallet.deposit(testWallet.myAccount, 100);
		double currentBalance = testWallet.getBalance();
		assertEquals(450, currentBalance,0.1);
	}

	@Test
	public void testWithdraw() {
		testWallet.withdraw(testWallet.myAccount, 20);
		assertEquals(330,testWallet.getBalance(),0.1);
	}

	@Test
	public void testGetBalance() {
		assertEquals(350,testWallet.getBalance(),0.1);
	}

	@Test
	public void testTransfer() {
		VirtualWallet testWallet2 = new VirtualWallet(0);
		testWallet.transfer(testWallet.myAccount, testWallet2.myAccount,100);
		assertEquals(250, testWallet.getBalance(),0.1);
		assertEquals(100,testWallet2.getBalance(),0.1);
	}

	@Test
	public void testGetHistory() {
		testWallet.deposit(testWallet.myAccount, 10);
		testWallet.withdraw(testWallet.myAccount, 20);
		testWallet.deposit(testWallet.myAccount, 30);
		Stack<String> testList = testWallet.getHistory();
		
		String firstTransaction = testList.elementAt(0);
		System.out.println(firstTransaction);
		Assert.assertTrue(firstTransaction.contains("Type: Deposit  Amount: $10"));
		
		String secondTransaction = testList.elementAt(1);
		Assert.assertTrue(secondTransaction.contains("Type: Withdraw  Amount: $20"));
		
		String thirdTransaction = testList.elementAt(2);
		Assert.assertTrue(thirdTransaction.contains("Type: Deposit  Amount: $30"));
		
	}

}
