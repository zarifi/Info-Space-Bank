package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		testWallet.withdraw(testWallet.myAccount, 100);
	}
	
	@Test(expected = InsufficientFundException.class)
	public void testWithdraw_negative_balance() throws Exception{
		testWallet.withdraw(testWallet.myAccount, 150);
	}
	
	@Test (expected = InsufficientFundException.class)
	public void testTransfer() throws Exception{
		VirtualWallet testWallet2 = new VirtualWallet(200);
		testWallet.transfer(testWallet.myAccount, testWallet2.myAccount, 150);
	}
	
	@Test (expected = NullUserException.class)
	public void testTransfer_null_recipient() throws Exception{
		testWallet.transfer(testWallet.myAccount, null, 150);
	}

}
