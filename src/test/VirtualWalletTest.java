package test;

import static org.junit.Assert.*;

import java.util.Stack;

import bank.VirtualWallet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VirtualWalletTest {

	VirtualWallet testWallet;
	@Before
	public void setUp() throws Exception {
		testWallet = new VirtualWallet(100);
	}

	@Test
	public void testDeposit() {
		double currentBalance = testWallet.getBalance();
		assertEquals(100, currentBalance,0.1);
	}

	@Test
	public void testWithdraw() {
		testWallet.withdraw(testWallet.myAccount, 10);
		assertEquals(90,testWallet.getBalance(),0.1);
	}

	@Test
	public void testGetBalance() {
		assertEquals(100,testWallet.getBalance(),0.1);
	}

	@Test
	public void testTransfer() {
		VirtualWallet testWallet2 = new VirtualWallet(0);
		testWallet.transfer(testWallet.myAccount, testWallet2.myAccount,30);
		assertEquals(70, testWallet.getBalance(),0.1);
		assertEquals(30,testWallet2.getBalance(),0.1);
	}

	@Test
	public void testGetHistory() {
		testWallet.deposit(testWallet.myAccount, 30);
		testWallet.withdraw(testWallet.myAccount, 10);
		testWallet.deposit(testWallet.myAccount, 10);
		Stack<String> testList = testWallet.getHistory();
		
		String firstTransaction = testList.elementAt(0);
		System.out.println(firstTransaction);
		Assert.assertTrue(firstTransaction.contains("Type: Deposit  Amount: $30"));
		
		String secondTransaction = testList.elementAt(1);
		Assert.assertTrue(secondTransaction.contains("Type: Withdraw  Amount: $10"));
		
		String thirdTransaction = testList.elementAt(2);
		Assert.assertTrue(thirdTransaction.contains("Type: Deposit  Amount: $10"));
		
	}

}
