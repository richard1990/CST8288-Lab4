/*
 * Code in this file was based on lecture materials provided by 
 * prof. Stanley Pieda (2015) personal communication.
 */
/**
 * datatransferobjects is the package for class placement	
 */
package datatransferobjects;
/**
 * This class holds all the data for a book's title, including its
 * title, ISBN, edition, and copyright.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class Title {
	/** String to hold a book's copyright */
	private String copyright;
	/** An integer to hold a book's edition number */
	private int editionNumber;
	/** String to hold a book's ISBN */
	private String isbn;
	/** String to hold a book's title */
	private String title;
	
	/** 
	 * Get method that returns the copyright.
	 * @return copyright as a String
	 */
	public String getCopyright() { return copyright; }
	/** 
	 * Get method that returns the edition number.
	 * @return editionNumber as an integer
	 */
	public int getEditionNumber() { return editionNumber; }
	/** 
	 * Get method that returns the ISBN.
	 * @return isbn as a String
	 */
	public String getISBN() { return isbn; }
	/** 
	 * Get method that returns the title.
	 * @return title as a String
	 */
	public String getTitle() { return title; }
	
	/**
	 * Set method that sets the copyright.
	 * @param    copyright   copyright to set as a String
	 */
	public void setCopyright(String copyright) { this.copyright = copyright; }
	/**
	 * Set method that sets the copyright.
	 * @param    editionNumber   editionNumber to set as an integer
	 */
	public void setEditionNumber(int editionNumber) { this.editionNumber = editionNumber; }
	/**
	 * Set method that sets the copyright.
	 * @param    isbn   isbn to set as a String
	 */
	public void setISBN(String isbn) { this.isbn = isbn; }
	/**
	 * Set method that sets the copyright.
	 * @param    title   title to set as a String
	 */
	public void setTitle(String title) { this.title = title; }
} // end class Title