/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * dataaccesslayer is the package for class placement	
 */
package dataaccesslayer;
// import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * This class sets up the connection to the server.
 * @author		Prof. Stan Pieda, Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class DataSource {
	/** Connection object. */
	private Connection connection = null;
	/** String to hold the connection name (localhost). */
	private String connectionString = "jdbc:mysql://localhost/books";
	/** String to hold the username 'scott'. */
	private String username = "scott";
	/** String to hold the password for username 'scott'. */
	private String password = "tiger";

	/**
	 * Default constructor
	 */
	public DataSource() { }
	
	/**
	 * Creates the connection.
	 * @return	connection	Connection object
	 */
	public Connection createConnection(){
		try {
			if (connection != null) { // check if a connection already exists
				System.out.println("Cannot create new connection, one exists already");
			}
			else {
				connection = DriverManager.getConnection(connectionString, username, password);
			}
		}
		catch(SQLException ex) { // catch any SQL exceptions
			ex.printStackTrace();
		}
		return connection; // return the Connection object
	} // end method createConnection
} // end class DataSource