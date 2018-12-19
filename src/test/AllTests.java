package test;

/**
 * This test suite is for all test classes except the ParallelComputer test case
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ VirtualWalletExceptionTest.class, VirtualWalletTest1.class,
		VirtualWalletTest2.class })
public class AllTests {

}
