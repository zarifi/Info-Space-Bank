package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bank.TransactionAccount;
import bank.VirtualWallet;
import exceptions.InsufficientFundException;
import exceptions.NullUserException;

public class VirtualWalletExceptionTest {

	public VirtualWallet testWallet;
	@Before
	public void setUp() throws Exception {
		testWallet = new VirtualWallet(100);
	}

	@Test(expected = InsufficientFundException.class)
	public void testWithdraw_zero_balance() throws Exception{
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.withdraw(currentAccount, 100);
	}
	
	@Test(expected = InsufficientFundException.class)
	public void testWithdraw_negative_balance() throws Exception{
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.withdraw(currentAccount, 150);
	}
	
	@Test (expected = InsufficientFundException.class)
	public void testTransfer() throws Exception{
		TransactionAccount currentAccount = testWallet.getAccount(1);
		VirtualWallet testWallet2 = new VirtualWallet(200);
		testWallet.transfer(currentAccount, testWallet2.getAccount(1), 150);
	}
	
	@Test (expected = NullUserException.class)
	public void testTransfer_null_recipient() throws Exception{
		TransactionAccount currentAccount = testWallet.getAccount(1);
		testWallet.transfer(currentAccount, null, 150);
	}

}
