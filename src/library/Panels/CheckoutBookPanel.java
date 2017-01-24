package library.Panels;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.Objects.Book;
import library.Objects.Library;
import library.Objects.User;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class CheckoutBookPanel extends JPanel {

	private Library library;
	private JList lstUsers;
	private JButton btnCheckout;
	private JScrollPane scrlUsers;
	private JButton btnBackToMenu;

	private DefaultListModel<User> usersModel = new DefaultListModel<User>();
	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();
	private JScrollPane scrlBooks;
	private JList lstBooks;
	private JLabel lblBG;

	public CheckoutBookPanel(Library library) {
		this.library = library;
		setupComponents();
		createEvents();
	}

	// Initializes components and sets up the JPanel
	public void setupComponents() {

		btnBackToMenu = new JButton();
		btnBackToMenu.setBounds(12, 23, 58, 52);
		btnBackToMenu.setBorderPainted(false);
		btnBackToMenu.setOpaque(false);
		btnBackToMenu.setIcon(Visuals.back);
		btnBackToMenu.setForeground(Visuals.white);
		btnBackToMenu.setFont(Visuals.uiFont);
		btnBackToMenu.setFocusable(false);
		btnBackToMenu.setBackground(Visuals.lBlue);

		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(0, 495, 795, 72);
		btnCheckout.setForeground(Visuals.white);
		btnCheckout.setFont(Visuals.uiFontBold);
		btnCheckout.setFocusable(false);
		btnCheckout.setBackground(Visuals.lBlue);

		scrlUsers = new JScrollPane();
		scrlUsers.setBorder(null);
		scrlUsers.setBounds(118, 156, 204, 319);

		scrlBooks = new JScrollPane();
		scrlBooks.setBorder(null);
		scrlBooks.setBounds(475, 156, 204, 319);

		lstUsers = new JList();
		lstUsers.setBorder(null);
		scrlUsers.setViewportView(lstUsers);
		Kit.setupList(lstUsers);

		lstBooks = new JList();
		lstBooks.setBorder(null);
		scrlBooks.setViewportView(lstBooks);
		Kit.setupList(lstBooks);

		lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.checkoutBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnCheckout);
		add(scrlUsers);
		add(btnBackToMenu);
		add(scrlBooks);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		addComponentListener(new ComponentAdapter() {
			// This will run every time this page is loaded
			public void componentShown(ComponentEvent arg0) {
				setUsersModel();
				setBooksModel();
			}
		});

		// Setting a new renderer so that the objects in the userModel are
		// printed nicely in the list
		lstUsers.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				((JLabel) renderer).setText("  " + ((User) value).getBasicDetails());
				return renderer;
			}
		});

		// Setting a new renderer so that the objects in the bookModel are
		// printed nicely in the list
		lstBooks.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				((JLabel) renderer).setText("  " + ((Book) value).getBasicDetails());
				return renderer;
			}
		});

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.menu);
			}
		});

		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If something is selected on both lists
				if (!lstUsers.isSelectionEmpty() && !lstBooks.isSelectionEmpty()) {

					User borrowing_user = (User) lstUsers.getSelectedValue();
					Book borrowing_book = (Book) lstBooks.getSelectedValue();
					
					library.checkoutBook(borrowing_user, borrowing_book);
				} else
					MsgPopup.showErrorMessage("Make sure you selected a user and a book!");
			}
		});

	}

	// This will be called every time this panel is viewed
	// It will refresh the list of all current users
	public void setUsersModel() {

		// Clearing out the current model
		usersModel.clear();

		// Adding all the current users to the model
		for (User user : library.getList_users())
			usersModel.addElement(user);

		lstUsers.setModel(usersModel);
	}

	// This will be called every time this panel is viewed
	// It will refresh the list of all current books
	public void setBooksModel() {

		// Clearing out the current model
		booksModel.clear();

		// Adding all the current users to the model
		for (Book book : library.getList_books())
			booksModel.addElement(book);

		lstBooks.setModel(booksModel);
	}
}
