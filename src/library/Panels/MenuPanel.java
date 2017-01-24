package library.Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import library.Objects.Library;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class MenuPanel extends JPanel {

	private Library library;
	private JButton btnAddUser;
	private JButton btnDeleteUser;
	private JButton btnAddBook;
	private JButton btnDeleteBook;
	private JButton btnSearchUsers;
	private JButton btnSearchBooks;
	private JButton btnLostBook;
	private JButton btnCheckoutBook;
	private JButton btnReturnBook;
	private JPanel pnlBookActions;
	private JLabel lblBG;
	private JButton btnPayFines;

	public MenuPanel(Library library) {
		this.library = library;
		setupComponents();
		createEvents();
	}

	// Initializes components and sets up the JPanel
	public void setupComponents() {

		btnAddUser = new JButton("Add User");
		btnAddUser.setBounds(26, 177, 192, 88);
		btnAddUser.setFocusable(false);
		btnAddUser.setForeground(Visuals.dBlue);
		btnAddUser.setFont(Visuals.uiFont);
		btnAddUser.setBackground(Visuals.lBeige);

		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBounds(26, 305, 192, 88);
		btnDeleteUser.setForeground(Visuals.dBlue);
		btnDeleteUser.setFont(Visuals.uiFont);
		btnDeleteUser.setFocusable(false);
		btnDeleteUser.setBackground(Visuals.lBeige);

		btnAddBook = new JButton("Add Book");
		btnAddBook.setBounds(580, 177, 192, 88);
		btnAddBook.setForeground(Visuals.dBlue);
		btnAddBook.setFont(Visuals.uiFont);
		btnAddBook.setFocusable(false);
		btnAddBook.setBackground(Visuals.lBeige);

		btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.setBounds(580, 305, 192, 88);
		btnDeleteBook.setForeground(Visuals.dBlue);
		btnDeleteBook.setFont(Visuals.uiFont);
		btnDeleteBook.setFocusable(false);
		btnDeleteBook.setBackground(Visuals.lBeige);

		btnSearchUsers = new JButton("Lookup Users");
		btnSearchUsers.setBounds(296, 113, 207, 82);
		btnSearchUsers.setForeground(Visuals.white);
		btnSearchUsers.setFont(Visuals.uiFont);
		btnSearchUsers.setFocusable(false);
		btnSearchUsers.setBackground(Visuals.black);

		btnSearchBooks = new JButton("Lookup Books");
		btnSearchBooks.setBounds(296, 203, 207, 81);
		btnSearchBooks.setForeground(Visuals.white);
		btnSearchBooks.setFont(Visuals.uiFont);
		btnSearchBooks.setFocusable(false);
		btnSearchBooks.setBackground(Visuals.black);

		btnPayFines = new JButton("Pay User Fines");
		btnPayFines.setBounds(261, 305, 277, 78);
		btnPayFines.setForeground(Visuals.white);
		btnPayFines.setFont(Visuals.uiFont);
		btnPayFines.setFocusable(false);
		btnPayFines.setBackground(Visuals.black);
		
		btnLostBook = new JButton("Report Lost Book");
		btnLostBook.setBounds(261, 393, 277, 78);
		btnLostBook.setForeground(Visuals.white);
		btnLostBook.setFont(Visuals.uiFont);
		btnLostBook.setFocusable(false);
		btnLostBook.setBackground(Visuals.black);

		btnCheckoutBook = new JButton("Checkout Book");
		btnCheckoutBook.setForeground(Visuals.white);
		btnCheckoutBook.setFont(Visuals.uiFontBold);
		btnCheckoutBook.setFocusable(false);
		btnCheckoutBook.setBackground(Visuals.lBlue);

		btnReturnBook = new JButton("Return Book");
		btnReturnBook.setForeground(Visuals.white);
		btnReturnBook.setFont(Visuals.uiFontBold);
		btnReturnBook.setFocusable(false);
		btnReturnBook.setBackground(Visuals.lBlue);

		lblBG = new JLabel();
		lblBG.setIcon(Visuals.menuBG);
		lblBG.setBounds(0, 0, 795, 567);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		pnlBookActions = new JPanel();
		pnlBookActions.setBounds(0, 488, 795, 79);
		pnlBookActions.setLayout(new GridLayout(1, 0, 0, 0));
		pnlBookActions.add(btnCheckoutBook);
		pnlBookActions.add(btnReturnBook);

		setLayout(null);
		add(btnSearchUsers);
		add(btnSearchBooks);
		add(btnPayFines);
		add(btnLostBook);
		add(pnlBookActions);
		add(btnDeleteBook);
		add(btnAddBook);
		add(btnDeleteUser);
		add(btnAddUser);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.addUser);
			}
		});

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.deleteUser);
			}
		});

		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.addBook);
			}
		});

		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.deleteBook);
			}
		});

		btnSearchUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.lookupUsers);
			}
		});

		btnSearchBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.lookupBooks);
			}
		});

		btnPayFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.payFines);
			}
		});
		
		btnLostBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.lostBook);
			}
		});

		btnCheckoutBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.checkoutBook);
			}
		});

		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.returnBook);
			}
		});
	}
}
