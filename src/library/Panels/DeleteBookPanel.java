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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import library.Objects.Book;
import library.Objects.Library;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class DeleteBookPanel extends JPanel {

	private Library library;
	private JList lstDelete;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JButton btnBackToMenu;

	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();
	private JLabel lblBG;

	public DeleteBookPanel(Library library) {
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

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(444, 256, 288, 109);
		btnDelete.setForeground(Visuals.dBlue);
		btnDelete.setFont(Visuals.uiFontBold);
		btnDelete.setFocusable(false);
		btnDelete.setBackground(Visuals.lBeige);

		
		lstDelete = new JList();
		lstDelete.setBorder(null);
		Kit.setupList(lstDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(85, 156, 231, 388);
		scrollPane.setViewportView(lstDelete);

		lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.deleteBookBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnDelete);
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

		// Setting a new renderer so that the objects in the bookModel are
		// printed nicely in the list
		lstDelete.setCellRenderer(new DefaultListCellRenderer() {
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

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If something is selected
				if (!lstDelete.isSelectionEmpty()) {
					Book deleting_book = (Book) lstDelete.getSelectedValue();
					boolean successDelete = library.deleteBook(deleting_book);

					// Removing the user from the list if they were deleted
					if (successDelete)
						booksModel.removeElement(lstDelete.getSelectedValue());

				} else
					MsgPopup.showErrorMessage("Make sure you selected a user!");
			}
		});
	}

	// This will be called every time this panel is viewed
	// It will refresh the list of all current books
	public void setBooksModel() {

		// Clearing out the current model
		booksModel.clear();

		// Adding all the current users to the model
		for (Book book : library.getList_books())
			booksModel.addElement(book);

		lstDelete.setModel(booksModel);
	}
}
