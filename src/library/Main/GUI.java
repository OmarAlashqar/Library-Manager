package library.Main;

import java.awt.CardLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import library.Objects.Library;
import library.Panels.AddBookPanel;
import library.Panels.AddUserPanel;
import library.Panels.CheckoutBookPanel;
import library.Panels.DeleteBookPanel;
import library.Panels.DeleteUserPanel;
import library.Panels.LookupBooksPanel;
import library.Panels.LookupUsersPanel;
import library.Panels.LostBookPanel;
import library.Panels.MenuPanel;
import library.Panels.PayFinesPanel;
import library.Panels.ReturnBookPanel;
import library.Utility.MsgPopup;

public class GUI extends JFrame {

	Library library;

	// This JPanel will hold all the contents that will be displayed
	private JPanel pnlContent = new JPanel();

	// CardLayout manages the panels (cards) that will be displayed
	private CardLayout cardLayout = new CardLayout();

	// Declaring all the JPanels (cards) that will be used
	private MenuPanel pnlMenu;
	private AddUserPanel pnlAddUser;
	private DeleteUserPanel pnlDeleteUser;
	private AddBookPanel pnlAddBook;
	private DeleteBookPanel pnlDeleteBook;
	private LookupUsersPanel pnlLookupUsers;
	private LookupBooksPanel pnlLookupBooks;
	private LostBookPanel pnlLostBook;
	private PayFinesPanel pnlPayFines;
	private CheckoutBookPanel pnlCheckoutBook;
	private ReturnBookPanel pnlReturnBook;

	public GUI(Library library) {
		super("Library System");
		this.library = library;
		setupPanels();

		// Setting up the error message pop-ups class
		MsgPopup.setupProperties();

		// Setting up the application window
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/library/Resources/book_128.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		setContentPane(pnlContent);

		// Delegating panel related variables to the library
		library.setCardLayout(cardLayout);
		library.setPnlContent(pnlContent);

		// Starting the program in the menu screen
		cardLayout.show(pnlContent, "pnlMenu");
	}

	// Initializes and adds all the JPanels that will be used
	public void setupPanels() {
		pnlMenu = new MenuPanel(library);
		pnlAddUser = new AddUserPanel(library);
		pnlDeleteUser = new DeleteUserPanel(library);
		pnlAddBook = new AddBookPanel(library);
		pnlDeleteBook = new DeleteBookPanel(library);
		pnlLookupUsers = new LookupUsersPanel(library);
		pnlLookupBooks = new LookupBooksPanel(library);
		pnlLostBook = new LostBookPanel(library);
		pnlPayFines = new PayFinesPanel(library);
		pnlCheckoutBook = new CheckoutBookPanel(library);
		pnlReturnBook = new ReturnBookPanel(library);

		pnlContent.setLayout(cardLayout);
		pnlContent.add(pnlMenu, "menu");
		pnlContent.add(pnlAddUser, "addUser");
		pnlContent.add(pnlDeleteUser, "deleteUser");
		pnlContent.add(pnlAddBook, "addBook");
		pnlContent.add(pnlDeleteBook, "deleteBook");
		pnlContent.add(pnlLookupUsers, "lookupUsers");
		pnlContent.add(pnlLookupBooks, "lookupBooks");
		pnlContent.add(pnlLostBook, "lostBook");
		pnlContent.add(pnlPayFines, "payFines");
		pnlContent.add(pnlCheckoutBook, "checkoutBook");
		pnlContent.add(pnlReturnBook, "returnBook");
	}
}
