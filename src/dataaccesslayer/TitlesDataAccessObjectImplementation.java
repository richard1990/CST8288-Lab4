/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * dataaccesslayer is the package for class placement	
 */
package dataaccesslayer;
// import statements
import java.util.List;
import java.util.ArrayList;
import datatransferobjects.Title;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class does the data access object implementation of adding, deleting, 
 * updating, etc. titles to/from the database as part of the data access layer.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class TitlesDataAccessObjectImplementation implements TitlesDataAccessObject {

	/**
	 * Overridden method that adds a title, including its ISBN,
	 * title name, edition number, and copyright year. 
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	@Override
	public int addTitle(Title title) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int nRecordsChanged = 0;
		try {
			DataSource dataSource = new DataSource();
			connection = dataSource.createConnection();
			// SQL statement to add a row to the database
			preparedStatement = connection.prepareStatement(
								"INSERT INTO Titles(ISBN, Title, EditionNumber, Copyright) "
								+"VALUES(?, ?, ?, ?)");
			// put the data using the get methods into the SQL statement
			preparedStatement.setString(1, title.getISBN());
			preparedStatement.setString(2, title.getTitle());
			preparedStatement.setInt(3, title.getEditionNumber());
			preparedStatement.setString(4, title.getCopyright());
			nRecordsChanged = preparedStatement.executeUpdate();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		} 
		finally {
			// close connections if they are still open
			try { if (preparedStatement != null) { preparedStatement.close(); }}
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (connection != null) { connection.close(); }}
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
		}
		return nRecordsChanged;
	} // end method addTitle
	
	/**
	 * Overridden method that deletes a title.
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	@Override
	public int deleteTitle(Title title) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int nRecordsChanged = 0;
		try {
			DataSource dataSource = new DataSource();
			connection = dataSource.createConnection();
			// SQL statement to delete a row from the database
			preparedStatement = connection.prepareStatement(
					"DELETE FROM Titles WHERE ISBN = ?");
			// put the data using the get method into the SQL statement
			preparedStatement.setString(1, title.getISBN());
			nRecordsChanged = preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// close connections if they are still open
			try { if (preparedStatement != null) { preparedStatement.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (connection != null) { connection.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
		}
		return nRecordsChanged;
	} // end method deleteTitle
	
	/**
	 * Overridden method that retrieves a List containing all titles.
	 * @return ArrayList of Title objects.
	 */
	@Override
	public List<Title> getAllTitles() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		ArrayList<Title> titles = null;
		try {
			DataSource dataSource = new DataSource();
			connection = dataSource.createConnection();
			// SQL statement to retrieve all columns from the database
			preparedStatement = connection.prepareStatement(
					"SELECT ISBN, Title, EditionNumber, Copyright FROM Titles ORDER BY ISBN");
			resultSet = preparedStatement.executeQuery();
			titles = new ArrayList<Title>();
			// iterate through the database and add to the 
			// ArrayList of titles
			while (resultSet.next()) {
				Title title = new Title();
				title.setISBN(resultSet.getString("ISBN"));
				title.setTitle(resultSet.getString("Title"));
				title.setEditionNumber(resultSet.getInt("EditionNumber"));
				title.setCopyright(resultSet.getString("Copyright"));
				titles.add(title);
			}	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// close connections if they are still open
			try { if (resultSet != null) { resultSet.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (preparedStatement != null) { preparedStatement.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (connection != null) { connection.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
		}
		return titles;
	} // end method getAllTitles
	
	/**
	 * Overridden method that retrieves a title by its ISBN.
	 * @param	isbn	String containing an ISBN.
	 * @return a Title object.
	 */
	@Override
	public Title getTitleByISBN(String isbn) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Title title = null;
		try {
			DataSource dataSource = new DataSource();
			connection = dataSource.createConnection();
			// SQL statement to get a row based on its ISBN
			preparedStatement = connection.prepareStatement(
					"SELECT ISBN, Title, EditionNumber, Copyright FROM Titles WHERE ISBN = ?");
			preparedStatement.setString(1, isbn);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				title = new Title();
				title.setISBN(resultSet.getString("ISBN"));
				title.setTitle(resultSet.getString("Title"));
				title.setEditionNumber(resultSet.getInt("EditionNumber"));
				title.setCopyright(resultSet.getString("Copyright"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// close connections if they are still open
			try { if (resultSet != null) { resultSet.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (preparedStatement != null) { preparedStatement.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (connection != null) { connection.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
		}
		return title;
	} // end method getTitleByISBN
	
	/**
	 * Overridden method that updates a title (title name, edition number,
	 * and copyright).
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	@Override
	public int updateTitle(Title title) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int nRecordsChanged = 0;
		try {
			DataSource dataSource = new DataSource();
			connection = dataSource.createConnection();
			// SQL statement to update a row
			preparedStatement = connection.prepareStatement(
					"UPDATE Titles SET Title = ?, "
					+"EditionNumber = ?, Copyright = ? "
					+"WHERE ISBN = ?");
			// put the data using the get methods into the SQL statement
			preparedStatement.setString(1, title.getTitle());
			preparedStatement.setInt(2, title.getEditionNumber());
			preparedStatement.setString(3, title.getCopyright());
			preparedStatement.setString(4, title.getISBN());
			nRecordsChanged = preparedStatement.executeUpdate();			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// close connections if they are still open
			try { if (preparedStatement != null) { preparedStatement.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
			try { if (connection != null) { connection.close(); } }
			catch(SQLException ex) { System.out.println(ex.getMessage()); }
		}
		return nRecordsChanged;
	} // end method updateTitle
} // end class TitleDataAccessObjectImplementation