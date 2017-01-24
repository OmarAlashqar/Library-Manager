package library.Objects;

import java.awt.CardLayout;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;

public class Library {

	// These will be delegated from the GUI object
	private CardLayout cardLayout;
	private JPanel pnlContent;

	private ArrayList<User> list_users = new ArrayList<User>();
	private ArrayList<Book> list_books = new ArrayList<Book>();

	private final String[] categories = { "Classic", "Thriller", "Mystery", "Drama", "Horror", "Fiction", "Non-Fiction",
			"Romance", "History" };

	public Library() {

		// Dummy objects used for testing
		list_users.add(new User("Black", "Jack", 103927));
		list_users.add(new User("Azoth", "Berry", 291840));
		list_users.add(new User("Zack", "Kar", 301829));
		list_users.add(new User("Fiora", "Sot", 401917));
		list_users.add(new User("Madeline", "Barber", 501628));

		list_books.add(new Book("Life", "Aston Blue", 102948, "Horror", 20.0, 1));
		list_books.add(new Book("1984", "George Orwell", 201723, "Fiction", 59.99, 5));
		list_books.add(new Book("Droplet", "Rick Bayley", 202723, "History", 3.45, 3));
		list_books.add(new Book("Quarters", "Picket Row", 122233, "Drama", 1.5, 3));
		list_books.add(new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", 102723, "Classic", 31.0, 5));
	}

	/////////////////////////////////////////
	// User related methods

	// Instantiates a new User object and adds it to the list of users.
	// Returns true if the user was successfully added.
	public boolean addUser(String firstName, String lastName, int studentNum) {

		// Checking for a duplicate user
		for (User user : list_users) {
			if (user.getStudentNum() == studentNum) {
				MsgPopup.showErrorMessage("A student with the student number\n" + studentNum + " already exists!");
				return false;
			}
		}

		// Since there is no duplicate, then create a new user
		list_users.add(new User(firstName, lastName, studentNum));
		MsgPopup.showSuccessMessage("User was successfully added!");
		return true;
	}

	// Removes the user instance from the list of users.
	// Returns true if the user was successfully deleted.
	public boolean deleteUser(User user) {

		// Checking if the user is allowed to be deleted
		if (user.getFinesDue() != 0)
			MsgPopup.showErrorMessage("User cannot be deleted!\nThey must pay their fines first.");
		else if (user.getBooksCheckedOut().size() != 0)
			MsgPopup.showErrorMessage("User cannot be deleted!\nThey must return all their books first.");
		else {
			int response = MsgPopup
					.showQuestionMessage("Are you sure you want to delete\n" + user.getBasicDetails() + "?");

			// If "Yes" is clicked
			if (response == JOptionPane.YES_OPTION) {
				list_users.remove(user);
				MsgPopup.showSuccessMessage("User was successfully deleted!");
				return true;
			}
		}

		// The user was not deleted
		return false;
	}

	/////////////////////////////////////////
	// Book related methods

	// Instantiates a new Book object and adds it to the list of books.
	// Returns true if the book was successfully created.
	public boolean addBook(String title, String author, int isbn, String category, double cost, int rating) {

		// Checking for a duplicate book
		for (Book book : list_books) {
			if (book.getIsbn() == isbn) {
				MsgPopup.showErrorMessage("A book with the isbn\n" + isbn + " already exists!");
				return false;
			}
		}

		// Since there is no duplicate, then create a new book
		list_books.add(new Book(title, author, isbn, category, cost, rating));
		MsgPopup.showSuccessMessage("Book was successfully added!");
		return true;
	}

	// Removes the book instance from the list of books.
	// Returns true if the book was successfully deleted.
	public boolean deleteBook(Book book) {

		// If the book is currently checked out by someone
		if (!book.getAvailability())
			MsgPopup.showErrorMessage(
					"This book cannot be deleted! It's currently\nchecked out by " + book.getOwner().getFullName());
		else {
			int response = MsgPopup.showQuestionMessage("Are you sure you want to delete\n" + book.getBasicDetails());

			// If "Yes" is clicked
			if (response == JOptionPane.YES_OPTION) {
				list_books.remove(book);
				MsgPopup.showSuccessMessage("Book was successfully deleted!");
				return true;
			}
		}
		return false;
	}

	// When a book is lost, the book is removed from the list of books
	// and the cost of the book is added to it's previous owner as a fine
	// Returns true if the book was reported
	public boolean reportedLost(Book book) {

		int response = MsgPopup.showQuestionMessage(
				"Are you sure you want to report the book\n" + book.getBasicDetails() + " being lost?");

		// If "Yes" is clicked
		if (response == JOptionPane.YES_OPTION) {
			book.getOwner().addFines(book.getCost());
			list_books.remove(book);

			MsgPopup.showSuccessMessage("Success! A fine of $" + book.getCost() + " has been added\nto "
					+ book.getOwner().getFullName() + "'s account.");
			return true;
		}
		return false;
	}

	// Checks if the proposed checkout is allowed to happen.
	// Returns false if the customer or the book don't exist, if the
	// customer isn't allowed to checkout any books, and if the book
	// is already checked out.
	public boolean transactionIsAllowed(User customer, Book book) {

		//////////// Checking user related requirements ////////////

		// Checking for fines
		if (customer.getFinesDue() > 5.0) {
			MsgPopup.showErrorMessage("Student has more than $5.00 fines,\nand isn't allowed to borrow books!");
			return false;
		}

		// Checking for number of books already borrowed
		if (customer.getBooksCheckedOut().size() >= 3) {
			MsgPopup.showErrorMessage(
					"The student already has three books checked out,\nand isn't allowed to borrow books!");
			return false;
		}

		//////////// Checking book related requirements ////////////

		// Checking for the availability of the book
		if (!book.getAvailability()) {
			MsgPopup.showErrorMessage("This book is already checked out!");
			return false;
		}

		// The transaction is allowed
		return true;
	}

	// Checks out a book from the library to the given user
	public void checkoutBook(User user, Book book) {

		if (transactionIsAllowed(user, book)) {
			int response = MsgPopup.showQuestionMessage(
					"Are you sure you want to checkout\n" + book.getTitle() + " to " + user.getFullName() + "?");

			// If "Yes" is clicked
			if (response == JOptionPane.YES_OPTION) {

				// Converting to a checked out state
				book.setAvailability(false);
				book.setDateBorrowed(ZonedDateTime.now());
				book.setOwner(user);

				// Adding the book to the customer's list of borrowed books
				ArrayList<Book> currentList = user.getBooksCheckedOut();
				currentList.add(book);
				user.setBooksCheckedOut(currentList);

				// Displaying when the book is due
				ZonedDateTime dateDue = ZonedDateTime.now().plusDays(14);
				MsgPopup.showSuccessMessage(
						"The book was successfully checkout out!\nIt's due on " + Kit.formatDate(dateDue));
			}
		}
	}

	public void returnBook(Book book) {
		int daysBorrowed = (int) Duration.between(book.getDateBorrowed(), ZonedDateTime.now()).toDays();

		// If the book was returned within 2 weeks
		if (daysBorrowed <= 14) {
			MsgPopup.showSuccessMessage("Thanks for returning the book on time!");
		}
		// The book was returning after more than 2 weeks
		else {
			int daysLate = daysBorrowed - 14;
			double fine = Math.floor((0.10 * daysLate) * 10) / 10;
			book.getOwner().addFines(fine);
			MsgPopup.showSuccessMessage("The book was returned " + daysLate + " days late!\nA fine of $" + fine
					+ " has been added to\nthe user " + book.getOwner().getFullName());
		}

		// Removing it from the user's possession
		book.getOwner().removeBook(book);

		// Returning it to the library
		book.setOwner(null);
		book.setAvailability(true);
		book.setDateBorrowed(null);
	}

	// JPanels will access this method to change which page is being displayed
	public void display(PANEL panel) {
		switch (panel) {
		case menu:
			cardLayout.show(pnlContent, "menu");
			break;
		case addUser:
			cardLayout.show(pnlContent, "addUser");
			break;
		case deleteUser:
			cardLayout.show(pnlContent, "deleteUser");
			break;
		case addBook:
			cardLayout.show(pnlContent, "addBook");
			break;
		case deleteBook:
			cardLayout.show(pnlContent, "deleteBook");
			break;
		case lookupUsers:
			cardLayout.show(pnlContent, "lookupUsers");
			break;
		case lookupBooks:
			cardLayout.show(pnlContent, "lookupBooks");
			break;
		case lostBook:
			cardLayout.show(pnlContent, "lostBook");
			break;
		case checkoutBook:
			cardLayout.show(pnlContent, "checkoutBook");
			break;
		case returnBook:
			cardLayout.show(pnlContent, "returnBook");
			break;
		case payFines:
			cardLayout.show(pnlContent, "payFines");
		}
	}

	/////////////////////////////////////////
	// Setters and Getters

	public ArrayList<User> getList_users() {
		return list_users;
	}

	public ArrayList<Book> getList_books() {
		return list_books;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public void setPnlContent(JPanel pnlContent) {
		this.pnlContent = pnlContent;
	}
}
