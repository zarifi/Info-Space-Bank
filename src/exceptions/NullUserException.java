package exceptions;

/**
 * @author Mohammad
 * 
 * The NullUserException class is triggered when user tries to access user's account which does not exist.
 * By assumption by creation of each user a corresponding account is also created therefore it sufficient to 
 * check the existence of the user in order to check the existence of the account.
 */


public class NullUserException extends RuntimeException{
	
	/**
	 * Auto generated serial Version UID
	 */
	private static final long serialVersionUID = -1648475327994729183L;

	public NullUserException(String message){
		super(message);
		
	}
}
