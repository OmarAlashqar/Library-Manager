package library.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.Objects.Library;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class AddBookPanel extends JPanel {

	private Library library;
	
	private JButton btnCreateBook;
	private JButton btnBackToMenu;
	private JTextField txtTitle;
	private JTextField txtAuthor;
	private JTextField txtISBN;
	private JTextField txtCost;
	private JComboBox cbCategory;
	private JComboBox cbRating;

	// This arraylist will be used to validate all forms at once
	private ArrayList<JTextField> arr_fields = new ArrayList<JTextField>();

	public AddBookPanel(Library library) {
		this.library = library;
		setupComponents();
		createEvents();
	}

	// Initializes components and sets up the JPanel
	public void setupComponents() {

		btnCreateBook = new JButton("Create Book");
		btnCreateBook.setBounds(16, 474, 768, 80);
		btnCreateBook.setForeground(Visuals.dBlue);
		btnCreateBook.setFont(Visuals.uiFontBold);
		btnCreateBook.setFocusable(false);
		btnCreateBook.setBackground(Visuals.lBeige);

		btnBackToMenu = new JButton();
		btnBackToMenu.setBounds(12, 23, 58, 52);
		btnBackToMenu.setBorderPainted(false);
		btnBackToMenu.setOpaque(false);
		btnBackToMenu.setIcon(Visuals.back);
		btnBackToMenu.setForeground(Visuals.white);
		btnBackToMenu.setFont(Visuals.uiFont);
		btnBackToMenu.setFocusable(false);
		btnBackToMenu.setBackground(Visuals.lBlue);

		txtTitle = new JTextField("Alphanumeric allowed");
		txtTitle.setBounds(46, 175, 314, 41);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		Kit.setupField(txtTitle);

		txtAuthor = new JTextField("Only letters allowed");
		txtAuthor.setBounds(46, 274, 314, 41);
		txtAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		Kit.setupField(txtAuthor);

		txtISBN = new JTextField("Up to 6 digits allowed");
		txtISBN.setBounds(46, 373, 314, 41);
		txtISBN.setHorizontalAlignment(SwingConstants.CENTER);
		Kit.setupField(txtISBN);

		txtCost = new JTextField("Decimal allowed");
		txtCost.setBounds(439, 274, 314, 41);
		txtCost.setHorizontalAlignment(SwingConstants.CENTER);
		Kit.setupField(txtCost);

		cbCategory = new JComboBox();
		cbCategory.setBounds(439, 175, 314, 41);
		cbCategory.setModel(new DefaultComboBoxModel(library.getCategories()));
		Kit.setupCombobox(cbCategory);

		cbRating = new JComboBox();
		cbRating.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		cbRating.setBounds(439, 373, 314, 41);
		Kit.setupCombobox(cbRating);

		JLabel lblBG = new JLabel();
		lblBG.setIcon(Visuals.addBookBG);
		lblBG.setBounds(0, 0, 797, 567);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnBackToMenu);
		add(btnCreateBook);
		add(txtTitle);
		add(txtAuthor);
		add(txtISBN);
		add(txtCost);
		add(cbCategory);
		add(cbRating);
		add(lblBG);

		// Initializing the array of text fields
		arr_fields.add(txtTitle);
		arr_fields.add(txtAuthor);
		arr_fields.add(txtISBN);
		arr_fields.add(txtCost);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		/////////////////////////////////////////////////////////
		// Form Validation

		txtTitle.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtTitle.getText().trim().equals("Alphanumeric allowed")) {
					txtTitle.setForeground(Visuals.black);
					txtTitle.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtTitle.getText().trim().equals("")) {
					txtTitle.setForeground(Visuals.gray);
					txtTitle.setText("Alphanumeric allowed");
				}
			}
		});

		txtAuthor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				if (input != ' ' && input != '.' && !Character.isLetter(input))
					e.consume();
			}
		});

		txtAuthor.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtAuthor.getText().trim().equals("Only letters allowed")) {
					txtAuthor.setForeground(Visuals.black);
					txtAuthor.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtAuthor.getText().trim().equals("")) {
					txtAuthor.setForeground(Visuals.gray);
					txtAuthor.setText("Only letters allowed");
				}
			}
		});

		txtISBN.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();

				// Maximum of 6 digits
				if (txtISBN.getText().length() >= 6)
					e.consume();
			}
		});

		txtISBN.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtISBN.getText().trim().equals("Up to 6 digits allowed")) {
					txtISBN.setForeground(Visuals.black);
					txtISBN.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtISBN.getText().trim().equals("")) {
					txtISBN.setForeground(Visuals.gray);
					txtISBN.setText("Up to 6 digits allowed");
				}
			}
		});

		txtCost.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				String input = txtCost.getText();
				int indexOfPeriod = input.indexOf('.');

				// Don't allow a period if it's the first character or already
				// exists
				if (e.getKeyChar() == '.') {
					if (input.equals("") || indexOfPeriod != -1)
						e.consume();
				} else if (!Character.isDigit(e.getKeyChar()))
					e.consume();

				// Only allow two decimal places after the period
				else if (indexOfPeriod != -1 && indexOfPeriod == input.length() - 3)
					e.consume();
			}
		});

		txtCost.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtCost.getText().trim().equals("Decimal allowed")) {
					txtCost.setForeground(Visuals.black);
					txtCost.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtCost.getText().trim().equals("")) {
					txtCost.setForeground(Visuals.gray);
					txtCost.setText("Decimal allowed");
				}
			}
		});

		/////////////////////////////////////////////////////////
		// Buttons

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPage();
				library.display(PANEL.menu);
			}
		});

		btnCreateBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If any of the forms hasn't been filled out
				if (!Kit.checkForm(arr_fields)) {
					MsgPopup.showErrorMessage("You didn't complete the form!");
				} else {

					String title = txtTitle.getText().trim();
					String author = txtAuthor.getText().trim();
					int isbn = Integer.parseInt(txtISBN.getText().trim());
					String category = (String) cbCategory.getSelectedItem();
					int rating = Integer.parseInt((String) cbRating.getSelectedItem());
					double cost = Math.round(Double.parseDouble(txtCost.getText().trim()) * 60) / 60;

					// If the user was successfully created
					if (library.addBook(title, author, isbn, category, cost, rating))
						resetPage();
				}

			}
		});
	}

	// Resets the page
	public void resetPage() {

		requestFocus();

		for (JTextField txtField : arr_fields)
			txtField.setForeground(Visuals.gray);

		txtTitle.setText("Alphanumeric allowed");
		txtAuthor.setText("Only letters allowed");
		txtISBN.setText("Up to 6 digits allowed");
		txtCost.setText("Decimal allowed");
		cbCategory.setSelectedIndex(0);
		cbRating.setSelectedIndex(0);
	}
}
