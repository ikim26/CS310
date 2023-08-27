/**
 *  This is my submission of the NoElementException.
 *  @author Isaac Kim G01201648
 */
public class NoElementException extends RuntimeException{
	/**
	 *  Default constructor with parameter.
	 *  @param message the message that prints out with the exception
	 */
	public NoElementException(String message){
		super(message);
	}
	/**
	 *  Default constructor with no parameter.
	 */
	public NoElementException(){
		super();
	}
}