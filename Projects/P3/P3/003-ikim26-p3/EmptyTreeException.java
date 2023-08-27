/**
 *  This is my submission of the EmptyTreeException.
 *  @author Isaac Kim G01201648
 */
public class EmptyTreeException extends RuntimeException{
	/**
	 *  Default constructor with parameter.
	 *  @param message the message that prints out with the exception
	 */
	public EmptyTreeException(String message){
		super(message);
	}
	/**
	 *  Default constructor with no parameter.
	 */
	public EmptyTreeException(){
		super();
	}
}