package library.Panels;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.Objects.Book;
import library.Objects.Library;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class ReturnBookPanel extends JPanel {

	private Library library;
	private JList lstBooks;
	private JButton btnReturn;
	private JScrollPane scrollPane;
	private JButton btnBackToMenu;

	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();
	private JLabel lblBG;

	public ReturnBookPanel(Library library) {
		setBounds(new Rectangle(0, 0, 797, 567));
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

		btnReturn = new JButton("Return");
		btnReturn.setBounds(444, 256, 288, 109);
		btnReturn.setForeground(Visuals.dBlue);
		btnReturn.setFont(Visuals.uiFontBold);
		btnReturn.setFocusable(false);
		btnReturn.setBackground(Visuals.lBeige);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(85, 156, 231, 388);

		lstBooks = new JList();
		lstBooks.setBorder(null);
		scrollPane.setViewportView(lstBooks);
		Kit.setupList(lstBooks);

		lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.returnBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnReturn);
		add(scrollPane);
		add(btnBackToMenu);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		addComponentListener(new ComponentAdapter() {
			// This will run every time this page is loaded
			public void componentShown(ComponentEvent arg0) {
				setBooksModel();
			}
		});

		// Setting a new renderer so that the objects in the userModel are
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

		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If something is selected
				if (!lstBooks.isSelectionEmpty()) {

					Book returning_book = (Book) lstBooks.getSelectedValue();

					int response = MsgPopup.showQuestionMessage(
							"Are you sure you want to return\n" + returning_book.getBasicDetails());

					// If "Yes" is clicked
					if (response == JOptionPane.YES_OPTION) {
						library.returnBook(returning_book);
						booksModel.removeElement(lstBooks.getSelectedValue());
					}
				} else
					MsgPopup.showErrorMessage("Make sure you selected a book!");
			}
		});

	}

	// This will be called every time this panel is viewed
	// It will refresh the list of all current books
	public void setBooksModel() {

		// Clearing out the current model
		booksModel.clear();

		// Adding all the current users to the model
		for (Book book : library.getList_books()) {

			// If the book is checked out
			if (!book.getAvailability())
				booksModel.addElement(book);
		}

		lstBooks.setModel(booksModel);
	}
}
