package library.Objects;

import java.util.ArrayList;

import library.Utility.Kit;
import library.Utility.MsgPopup;

public class User {

	private String firstName, lastName;
	private int studentNum;
	private double finesDue;

	private ArrayList<Book> booksCheckedOut = new ArrayList<Book>();

	public User(String firstName, String lastName, int studentNum) {
		this.firstName = Kit.correctCapitalization(firstName);
		this.lastName = Kit.correctCapitalization(lastName);
		this.studentNum = studentNum;
	}

	// Resets the fines due
	public void clearFines() {

		if (finesDue == 0.0)
			MsgPopup.showErrorMessage("User already doesn't have any fines due!");
		else {
			finesDue = 0.0;
			MsgPopup.showSuccessMessage("All outstanding fines were successfully cleared!");
		}
	}

	// Removes the book from the list of borrowed books
	public void removeBook(Book book) {
		booksCheckedOut.remove(book);
	}

	// Returns a sentence that describes the user
	public String getBasicDetails() {
		return (firstName + " " + lastName + " (" + studentNum + ")");
	}

	// Returns the first and last name of the user in one string
	public String getFullName() {
		return (firstName + " " + lastName);
	}

	// Adds the amount given to the current fines due
	public void addFines(double fines) {
		finesDue += fines;
	}

	/////////////////////////////////////////
	// Setters and Getters

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public double getFinesDue() {
		return finesDue;
	}

	public ArrayList<Book> getBooksCheckedOut() {
		return booksCheckedOut;
	}

	public void setBooksCheckedOut(ArrayList<Book> booksCheckedOut) {
		this.booksCheckedOut = booksCheckedOut;
	}

}
