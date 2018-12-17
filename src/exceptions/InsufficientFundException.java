package exceptions;

/**
 * @author Mohammad
 * 
 * The InsufficientFundException (Unchecked) class is triggered when we try to withdraw more than available fund in an accont
 *  This class can be used to check Transactions like: Withdraw, Transfer
 */


public class InsufficientFundException extends RuntimeException {
		
	public InsufficientFundException(String message){
		
		super(message);
	}
	
}
