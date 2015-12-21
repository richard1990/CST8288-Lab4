/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * businesslayer is the package for class placement	
 */
package businesslayer;
// import statements
import java.util.List;
import dataaccesslayer.TitlesDataAccessObject;
import dataaccesslayer.TitlesDataAccessObjectImplementation;
import datatransferobjects.Title;
/**
 * This class holds the business logic for adding, deleting, updating, 
 * titles to/from the database as part of the business layer.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class TitleBusinessLogic {
	/** Integer to indicate max String length for copyright. */
	private static final int COPYRIGHT_MAX_LENGTH = 4;
	/** Integer to indicate max String length for ISBN. */
	private static final int ISBN_MAX_LENGTH = 20;
	/** Integer to indicate max String length for title. */
	private static final int TITLE_MAX_LENGTH = 100;
	/** TitlesDataAccessObject object to call class methods. */
	private TitlesDataAccessObject titlesDataAccessObject = null;
	
	/**
	 * Default constructor.
	 */
	public TitleBusinessLogic() {
		titlesDataAccessObject = new TitlesDataAccessObjectImplementation();
	}
	
	/**
	 * Adds a title based on the Title object passed to the method.
	 * @param	title	Title object.
	 * @return the number of titles added (rows).
	 * @throws ValidationException if data is incorrect.
	 */
	public int addTitle(Title title) throws ValidationException {
		cleanTitle(title);
		validateTitle(title);
		validateISBN(title);
		return titlesDataAccessObject.addTitle(title);
	}
	
	/**
	 * Deletes a title based on the Title object passed to the method.
	 * @param	title	Title object.
	 * @return the number of titles deleted (rows).
	 */
	public int deleteTitle(Title title) {
		return titlesDataAccessObject.deleteTitle(title);
	}
	
	/**
	 * Updates a title based on the Title object passed to the method.
	 * @param	title	Title object.
	 * @return the number of titles updated (rows).
	 * @throws ValidationException if data is incorrect.
	 */
	public int updateTitle(Title title) throws ValidationException {
		cleanTitle(title);
		validateTitle(title);
		return titlesDataAccessObject.updateTitle(title);
	}
	
	/**
	 * Returns all Title objects in a List.
	 * @return all Title objects in a List.
	 */
	public List<Title> getAllTitles() {
		return titlesDataAccessObject.getAllTitles();
	}
	
	/**
	 * Method that retrieves a title based on its ISBN.
	 * @param	isbn	String containing an ISBN.
	 * @return the Title object containing the ISBN passed to the parameter.
	 */
	public Title getTitleByISBN(String isbn) {
		return titlesDataAccessObject.getTitleByISBN(isbn);
	}

	/**
	 * Void method that cleans a Title.
	 * @param	title	Title object.
	 */
	private void cleanTitle(Title title) {
		if(title.getTitle() != null){ 
			title.setTitle(title.getTitle().trim());
		}
	}
	
	/**
	 * Void method that validates an integer.
	 * @param	value		integer containing the actual data.
	 * @param	fieldName	the name of the field being validated (e.g. edition number).
	 * @throws ValidationException if data is incorrect.
	 */
	private void validateInt(int value, String fieldName) throws ValidationException {
		if(value < 0) {
			throw new ValidationException(String.format("%s cannot be less than 0",
					fieldName));
		}
	}
	
	/**
	 * Void method that validates a String.
	 * @param	value			String containing the actual data.
	 * @param	fieldName		the name of the field being validated (ISBN, Title, etc.).
	 * @param	maxLength		the max length of the String.
	 * @param	isNullAllowed	boolean to determine if a null field is allowed.
	 * @throws ValidationException if data is incorrect.
	 */
	private void validateString(String value, String fieldName, int maxLength, boolean isNullAllowed)
	    throws ValidationException {		
		if (value == null && isNullAllowed) {
			// return; // null permitted, nothing to validate
		}
		else if (value == null && ! isNullAllowed) {
		    throw new ValidationException(String.format("%s cannot be null", 
		    		fieldName));
		}
		else if (value.length() == 0) {
			throw new ValidationException(String.format("%s cannot be empty or only whitespace", 
					fieldName));
		}
		else if (value.length() > maxLength) {
			throw new ValidationException(String.format("%s cannot exceed %d characters", 
					fieldName, maxLength));
		}
	}
	
	/**
	 * Void method that validates the ISBN to make sure a duplicate
	 * ISBN is not entered.
	 * @param	title	Title object.
	 * @throws ValidationException if data is incorrect.
	 */
	private void validateISBN(Title title) throws ValidationException {	
		// create a TitleBusinessLogic object, make a List of Titles equal
		// to that object and call the getAllTitles method, then iterate
		// through each member of the List and check if the same ISBN as the
		// one entered already exists and tell user if a match was found
		TitleBusinessLogic logic = new TitleBusinessLogic();
		List<Title> list = logic.getAllTitles();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getISBN().equals(title.getISBN())) {
				throw new ValidationException(String.format("ISBN already exists"));
			}
		}
	}
	
	/**
	 * Void method that calls validateString and validateInt methods, passing the 
	 * Title object to these methods for validation.
	 * @param	title	Title object
	 * @throws ValidationException if data is incorrect.
	 */
	private void validateTitle(Title title) throws ValidationException {
		validateString(title.getISBN(), "ISBN", ISBN_MAX_LENGTH, false);
		validateString(title.getTitle(), "Title", TITLE_MAX_LENGTH, false);
		validateString(title.getCopyright(), "Copyright", COPYRIGHT_MAX_LENGTH, false);
		validateInt(title.getEditionNumber(), "Edition Number");
	}	
} // end class TitleBusinessLogic