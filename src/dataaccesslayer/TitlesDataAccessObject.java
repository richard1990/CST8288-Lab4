/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * dataaccesslayer is the package for class placement	
 */
package dataaccesslayer;
//import statements
import java.util.List;
import datatransferobjects.Title;
/**
 * This interface holds the methods to add, delete, update, etc.
 * to/from the database as part of the data access layer.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public interface TitlesDataAccessObject {
	/**
	 * Adds a title.
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	int addTitle(Title title);
	
	/**
	 * Deletes a title.
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	int deleteTitle(Title title);
	
	/**
	 * Retrieves a List containing all titles.
	 * @return ArrayList of Title objects.
	 */
	List<Title> getAllTitles();
	
	/**
	 * Retrieves a title by its ISBN.
	 * @param	isbn	String containing ISBN.
	 * @return Title object.
	 */
	Title getTitleByISBN(String isbn);
	
	/**
	 * Updates a title.
	 * @param	title	Title object.
	 * @return row count for SQL DML statements using interface PreparedStatement's method executeUpdate().
	 */
	int updateTitle(Title title);
} // end interface TitleDataAccessObject