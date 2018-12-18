package test;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

/**
 * 
 * @author Mohammad
 * This Test case makes use of JUnit ParallelComputer library to concurrently run all other test classes
 * and their test methods. This is to test if our library works correctly if there are concurrent access
 * to the library.
 */

public class VirtualWalletClassParallel {

	@Test
	public void test(){
		Class[] classes = {VirtualWalletTest1.class, VirtualWalletTest2.class, VirtualWalletExceptionTest.class};
		
		JUnitCore.runClasses(new ParallelComputer(true,true),classes);
	}
}
