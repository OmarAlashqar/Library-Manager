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

public class LostBookPanel extends JPanel {

	private Library library;
	private JList lstBooks;
	private JButton btnReport;
	private JScrollPane scrollPane;
	private JButton btnBackToMenu;

	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();

	public LostBookPanel(Library library) {
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

		btnReport = new JButton("Report");
		btnReport.setBounds(444, 256, 288, 109);
		btnReport.setForeground(Visuals.dBlue);
		btnReport.setFont(Visuals.uiFontBold);
		btnReport.setFocusable(false);
		btnReport.setBackground(Visuals.lBeige);

		lstBooks = new JList();
		lstBooks.setBorder(null);
		Kit.setupList(lstBooks);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(85, 156, 231, 388);
		scrollPane.setViewportView(lstBooks);

		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.lostBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnReport);
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

		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If something is selected
				if (!lstBooks.isSelectionEmpty()) {
					Book book = (Book) lstBooks.getSelectedValue();

					boolean successLost = library.reportedLost(book);

					if (successLost)
						booksModel.removeElement(lstBooks.getSelectedValue());

				} else
					MsgPopup.showErrorMessage("Make sure you selected a user!");
			}
		});
	}

	// This will be called every time this panel is viewed
	// It will refresh the list of all current users
	public void setBooksModel() {

		// Clearing out the current model
		booksModel.clear();

		// Adding all the current users to the model
		for (Book book : library.getList_books()) {
			if (!book.getAvailability())
				booksModel.addElement(book);
		}

		lstBooks.setModel(booksModel);
	}
}
