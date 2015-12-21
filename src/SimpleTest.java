/*
 *  Code in this file was based on lecture materials provided by 
 *  prof. Stanley Pieda (2015) personal communication.
 */
// import statements
import java.util.List;
import java.util.UUID;
import datatransferobjects.Title;
import businesslayer.TitleBusinessLogic;
import businesslayer.ValidationException;
/**
 * This class generates test data for the whole project to show that
 * everything works the way its supposed to, that is, you can add, update, 
 * delete, and see all data in the List of Titles.
 * @author		Richard Barney
 * @version		1.0.0 March 30, 2015
 */
public class SimpleTest {
	/**
	 * Void method that generates a UUID valid for the database.
	 */
	public void simpleTest() {
		// this String will hold the randomly generated UUID cut down to 20 chars
		// to kind of function as a mock-ISBN
		String testRecordPrimaryKey = UUID.randomUUID().toString().substring(0, 20);
		try {
			// create the necessary objects
			TitleBusinessLogic logic = new TitleBusinessLogic();
			List<Title> list = null;
			Title title = null;
			
			// print all the titles in the list
			System.out.println("Printing Titles");
			list = logic.getAllTitles();
			printTitles(list);
			
			// print one title based on its ISBN by passing the ISBN
			// into getTitleByISBN
			System.out.println("Printing one Title");
			title = logic.getTitleByISBN("0132121360");
			printTitle(title);
			System.out.println();
			
			// attempt to insert a new row of data containing the same ISBN
			// that already exists. this will not work and a message will display
			// saying as much
			System.out.println("Attempting insert using existing ISBN");
			try {
				title = new Title();
				title.setISBN("0132121360");
				title.setTitle("Android for Programmers: An App-Driven Approach");
				title.setEditionNumber(1);
				title.setCopyright("2012");
				logic.addTitle(title);
			}
			catch(ValidationException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();

			// insert a new title by creating a new Title object, using the setters to
			// generate data, and then add it to the list and display all the elements
			System.out.println("Inserting one Title");
			title = new Title();
			title.setISBN(testRecordPrimaryKey);
			title.setTitle("Test Add Title");
			title.setEditionNumber(42);
			title.setCopyright("2042");
			logic.addTitle(title);
			list = logic.getAllTitles();
			printTitles(list);
			
			// update inserted title - first get the ISBN of the last element in the list
			// then create a new Title object and use the setters to set up the object and
			// then call updateTitle to update that row based on the ISBN, then display
			// all the elements again
			System.out.println("Updating inserted Title");
			String sISBN = list.get(list.size() - 1).getISBN();
			title = new Title();
			title.setISBN(sISBN);
			title.setTitle("Test Update Title");
			title.setEditionNumber(1764);
			title.setCopyright("1815");
			logic.updateTitle(title);
			list = logic.getAllTitles();
			printTitles(list);
			
			// delete updated title by finding the last element in the list and
			// calling deleteTitle then display all the elements again
			System.out.println("Deleting updated Title");
			title = list.get(list.size() - 1);
			logic.deleteTitle(title);
			list = logic.getAllTitles();
			printTitles(list);
		} // end try
		catch(ValidationException e) {
			System.err.println(e.getMessage());
		} // end catch
	} // end method simpleTest
	
	/**
	 * Void method that prints a single book title, including its
	 * ISBN, title, edition, and copyright.
	 * @param	title	Title object.
	 */
	private static void printTitle(Title title) {
    	String output = String.format("%s, %s, %d, %s",
    			title.getISBN(),
    			title.getTitle(),
    			title.getEditionNumber(),
    			title.getCopyright());
    	System.out.println(output);
	} // end method printTitle

	/**
	 * Void method that prints a List of titles by looping through
	 * the List and calling printTitle each time.
	 * @param	titles	List of Titles.
	 */
	private static void printTitles(List<Title> titles){
		for(Title title: titles){
			printTitle(title);
		}
		System.out.println();
	} // end method printTitles
} // end class SimpleTest