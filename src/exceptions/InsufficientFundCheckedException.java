package exceptions;

public class InsufficientFundCheckedException extends Exception{

	public InsufficientFundCheckedException(String message){
		super(message);
	}
}
