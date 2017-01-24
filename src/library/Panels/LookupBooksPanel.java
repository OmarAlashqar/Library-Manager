package library.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import library.Objects.Book;
import library.Objects.Library;
import library.Objects.User;
import library.Utility.Kit;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class LookupBooksPanel extends JPanel {

	private Library library;
	private JList lstBooks;
	private JScrollPane scrlBooks;
	private JButton btnBackToMenu;

	private DefaultListModel<User> usersModel = new DefaultListModel<User>();
	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();
	private JLabel lblTitle;
	private JLabel lblBG;
	private JComboBox cbRating;
	private JLabel lblAuthor;
	private JLabel lblCategory;
	private JLabel lblRating;
	private JLabel lblIsbn;
	private JLabel lblCost;
	private JComboBox cbCategory;
	private JLabel lblDetailsCover;
	private JLabel lblOwner;

	public LookupBooksPanel(Library library) {
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

		lstBooks = new JList();
		lstBooks.setBorder(null);
		Kit.setupList(lstBooks);

		scrlBooks = new JScrollPane();
		scrlBooks.setBorder(null);
		scrlBooks.setBounds(85, 310, 231, 234);
		scrlBooks.setViewportView(lstBooks);
		
		lblDetailsCover = new JLabel();
		lblDetailsCover.setBounds(402, 89, 395, 478);
		lblDetailsCover.setIcon(Visuals.detailsCoverBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(lblDetailsCover);
		add(scrlBooks);
		add(btnBackToMenu);

		lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(Visuals.uiFontBold);
		lblTitle.setForeground(Visuals.white);
		lblTitle.setBounds(402, 118, 392, 66);
		add(lblTitle);

		lblAuthor = new JLabel();
		lblAuthor.setForeground(new Color(242, 242, 242));
		lblAuthor.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblAuthor.setBounds(538, 223, 247, 30);
		add(lblAuthor);

		lblCategory = new JLabel();
		lblCategory.setForeground(new Color(242, 242, 242));
		lblCategory.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblCategory.setBounds(558, 263, 176, 30);
		add(lblCategory);

		lblRating = new JLabel();
		lblRating.setForeground(new Color(242, 242, 242));
		lblRating.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblRating.setBounds(530, 305, 143, 30);
		add(lblRating);

		lblIsbn = new JLabel();
		lblIsbn.setForeground(new Color(242, 242, 242));
		lblIsbn.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblIsbn.setBounds(509, 345, 204, 30);
		add(lblIsbn);

		lblCost = new JLabel();
		lblCost.setForeground(new Color(242, 242, 242));
		lblCost.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblCost.setBounds(519, 387, 204, 30);
		add(lblCost);

		DefaultComboBoxModel categoriesModel = new DefaultComboBoxModel(library.getCategories());
		categoriesModel.insertElementAt("All Categories", 0);
		
		lblOwner = new JLabel();
		lblOwner.setHorizontalAlignment(SwingConstants.CENTER);
		lblOwner.setForeground(new Color(242, 242, 242));
		lblOwner.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblOwner.setBounds(401, 429, 395, 101);
		add(lblOwner);
		
		cbCategory = new JComboBox();
		cbCategory.setBounds(85, 156, 231, 49);
		cbCategory.setModel(categoriesModel);
		cbCategory.setSelectedIndex(0);
		Kit.setupCombobox(cbCategory);
		add(cbCategory);

		cbRating = new JComboBox();
		cbRating.setBounds(85, 253, 231, 49);
		cbRating.setModel(new DefaultComboBoxModel(new String[] { "All Ratings", "1", "2", "3", "4", "5" }));
		Kit.setupCombobox(cbRating);
		add(cbRating);

		lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.searchBooksBG);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		addComponentListener(new ComponentAdapter() {
			// This will run every time this page is loaded
			public void componentShown(ComponentEvent arg0) {
				setBooksModel();
				lblDetailsCover.setVisible(true);
			}
		});
		
		cbCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBooksModel();
			}
		});
		

		cbRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBooksModel();
			}
		});

		lstBooks.addListSelectionListener(new ListSelectionListener() {
			// This will run every time a new user is selected from the list
			public void valueChanged(ListSelectionEvent e) {

				// If a book is selected
				if (lstBooks.getSelectedIndex() != -1) {
					Book book = (Book) lstBooks.getSelectedValue();

					lblTitle.setText(book.getTitle());
					lblAuthor.setText(book.getAuthor());
					lblCategory.setText(book.getCategory());
					lblRating.setText(book.getRating() + " / 5");
					lblIsbn.setText(book.getIsbn() + "");
					lblCost.setText(book.getCost() + "");
					
					if (book.getAvailability())
						lblOwner.setText("Available for checkout");
					else{
						String owner = book.getOwner().getFullName();
						lblOwner.setText("<html><p style='text-align:center'>Checked out by<br>" + owner +"</p></html>");
					}
					
					lblDetailsCover.setVisible(false);
				}
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
				lstBooks.clearSelection();

				cbCategory.setSelectedIndex(0);
				cbRating.setSelectedIndex(0);
				lblTitle.setText("");
				lblAuthor.setText("");
				lblCategory.setText("");
				lblRating.setText("");
				lblIsbn.setText("");
				lblCost.setText("");

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

			if (cbCategory.getSelectedItem().equals("All Categories")) {

				if (cbRating.getSelectedItem().equals("All Ratings"))
					booksModel.addElement(book);
				else if (book.getRating() == Integer.parseInt(cbRating.getSelectedItem()+""))
					booksModel.addElement(book);
			} else if (book.getCategory().equals(cbCategory.getSelectedItem()+"")) {

				if (cbRating.getSelectedItem().equals("All Ratings"))
					booksModel.addElement(book);
				else if (book.getRating() == Integer.parseInt(cbRating.getSelectedItem()+""))
					booksModel.addElement(book);
			}
		}

		lstBooks.setModel(booksModel);
	}
}
