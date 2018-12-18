package exceptions;

/**
 * 
 * @author Mohammad
 * Checked exception class for detecting null user, used for transfer transaction
 *
 */

public class NullUserCheckedException extends Exception{

	/**
	 * Auto generated serial Version UID
	 */
	private static final long serialVersionUID = 5843478257900831223L;

	public NullUserCheckedException(String message){
		super(message);
	}
}
