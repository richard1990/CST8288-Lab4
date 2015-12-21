/*
 *  Code in this file was based on lecture materials provided by 
 *  prof. Stanley Pieda (2015) personal communication.
 */
/**
 * This class launches the program.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class Launcher {
	/**
	 * Entry point "main()" as required by the JVM.
	 * @param	args	standard command line parameters (arguments) as a string array
	 */
	public static void main(String[] args) {
		// create new SimpleTest and generate the data
		(new SimpleTest()).simpleTest();
	} // end method main
} // end class Launcher
