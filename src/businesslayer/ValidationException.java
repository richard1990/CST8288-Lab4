/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * businesslayer is the package for class placement	
 */
package businesslayer;
/**
 * This class holds constructors that indicate data validation exceptions.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class ValidationException extends Exception {
	/** Eclipse-generated serialVersionUID */
	private static final long serialVersionUID = 4564171818375202130L;

	/**
	 * Default constructor.
	 */
	public ValidationException(){
		super("Data not in valid format");
	}
	
	/**
	 * Parameterized constructor that takes a String.
	 * @param	message		String containing a message.
	 */
	public ValidationException(String message){
		super(message);
	}
	
	/**
	 * Parameterized constructor that takes a String and Throwable.
	 * @param	message		String containing a message.
	 * @param	throwable	Throwable object.
	 */
	public ValidationException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	/**
	 * Parameterized constructor that takes a Throwable.
	 * @param	throwable	Throwable object.
	 */
	public ValidationException(Throwable throwable){
		super(throwable);
	}
} // end class ValidationException