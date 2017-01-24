
package library.Objects;

import java.time.Duration;
import java.time.ZonedDateTime;

import library.Utility.Kit;

public class Book {

	private User owner;
	private String title, author, category;
	private int isbn, rating;
	private double cost;
	private boolean availability;
	private ZonedDateTime dateBorrowed;

	public Book(String title, String author, int isbn, String category, double cost, int rating) {
		this.title = Kit.correctCapitalization(title);
		this.author = Kit.correctCapitalization(author);
		this.isbn = isbn;
		this.category = category;
		this.cost = cost;
		this.rating = rating;

		// The book is available for checkout right away
		availability = true;
	}

	// Returns the number of days the book has been checked out for
	public int calculateDaysCheckedOut() {
		ZonedDateTime dateNow = ZonedDateTime.now();
		return (int) Duration.between(dateBorrowed, dateNow).toDays();
	}

	// Returns a sentence that describes the book
	public String getBasicDetails() {
		return (title + " - by " + author + " (" + isbn + ")");
	}

	/////////////////////////////////////////
	// Setters and Getters

	public User getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public int getIsbn() {
		return isbn;
	}

	public int getRating() {
		return rating;
	}

	public double getCost() {
		return cost;
	}

	public boolean getAvailability() {
		return availability;
	}

	public ZonedDateTime getDateBorrowed() {
		return dateBorrowed;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setDateBorrowed(ZonedDateTime dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

}
