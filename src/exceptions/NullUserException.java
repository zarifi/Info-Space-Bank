package exceptions;

/**
 * @author Mohammad
 * 
 * The NullUserException class is triggered when user tries to access user's account which does not exist.
 * By assumption by creation of each user a corresponding account is also created therefore it sufficient to 
 * check the existence of the user in order to check the existence of the account.
 */


public class NullUserException extends RuntimeException{
	
	public NullUserException(String message){
		super(message);
		
	}

}
