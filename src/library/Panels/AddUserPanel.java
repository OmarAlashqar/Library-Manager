package library.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import library.Objects.Library;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class AddUserPanel extends JPanel {

	private Library library;
	
	private JButton btnCreateUser;
	private JButton btnBackToMenu;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtStudentNumber;

	// This arraylist will be used to validate all forms at once
	private ArrayList<JTextField> arr_fields = new ArrayList<JTextField>();

	public AddUserPanel(Library library) {
		this.library = library;
		setupComponents();
		createEvents();
	}

	// Initializes components and sets up the JPanel
	public void setupComponents() {

		btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(16, 474, 768, 80);
		btnCreateUser.setForeground(Visuals.dBlue);
		btnCreateUser.setFont(Visuals.uiFontBold);
		btnCreateUser.setFocusable(false);
		btnCreateUser.setBackground(Visuals.lBeige);

		btnBackToMenu = new JButton();
		btnBackToMenu.setBounds(12, 23, 58, 52);
		btnBackToMenu.setBorderPainted(false);
		btnBackToMenu.setOpaque(false);
		btnBackToMenu.setIcon(Visuals.back);
		btnBackToMenu.setForeground(Visuals.white);
		btnBackToMenu.setFont(Visuals.uiFont);
		btnBackToMenu.setFocusable(false);
		btnBackToMenu.setBackground(Visuals.lBlue);

		txtFirstName = new JTextField("Only letters allowed");
		txtFirstName.setBounds(413, 202, 314, 43);
		Kit.setupField(txtFirstName);

		txtLastName = new JTextField("Only letters allowed");
		txtLastName.setBounds(413, 260, 314, 43);
		Kit.setupField(txtLastName);

		txtStudentNumber = new JTextField("Up to 6 digits allowed");
		txtStudentNumber.setBounds(413, 348, 314, 43);
		Kit.setupField(txtStudentNumber);

		JLabel lblBG = new JLabel();
		lblBG.setIcon(Visuals.addUserBG);
		lblBG.setBounds(0, 0, 797, 567);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnBackToMenu);
		add(btnCreateUser);
		add(txtFirstName);
		add(txtLastName);
		add(txtStudentNumber);
		add(lblBG);

		// Initializing the array of fields
		arr_fields.add(txtFirstName);
		arr_fields.add(txtLastName);
		arr_fields.add(txtStudentNumber);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		/////////////////////////////////////////////////////////
		// Form Validation

		txtFirstName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});

		txtFirstName.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtFirstName.getText().trim().equals("Only letters allowed")) {
					txtFirstName.setForeground(Visuals.black);
					txtFirstName.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtFirstName.getText().trim().equals("")) {
					txtFirstName.setForeground(Visuals.gray);
					txtFirstName.setText("Only letters allowed");
				}
			}
		});

		txtLastName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isLetter(e.getKeyChar()))
					e.consume();
			}
		});

		txtLastName.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtLastName.getText().trim().equals("Only letters allowed")) {
					txtLastName.setForeground(Visuals.black);
					txtLastName.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtLastName.getText().trim().equals("")) {
					txtLastName.setForeground(Visuals.gray);
					txtLastName.setText("Only letters allowed");
				}
			}
		});

		txtStudentNumber.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				
				// Maximum of 6 digits
				if (txtStudentNumber.getText().length() >= 6)
					e.consume();
			}
		});

		txtStudentNumber.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtStudentNumber.getText().trim().equals("Up to 6 digits allowed")) {
					txtStudentNumber.setForeground(Visuals.black);
					txtStudentNumber.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtStudentNumber.getText().trim().equals("")) {
					txtStudentNumber.setForeground(Visuals.gray);
					txtStudentNumber.setText("Up to 6 digits allowed");
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

		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If any of the forms hasn't been filled out
				if (!Kit.checkForm(arr_fields)) {
					MsgPopup.showErrorMessage("You didn't complete the form!");
				} else {

					String firstName = txtFirstName.getText().trim();
					String lastName = txtLastName.getText().trim();
					int studentNumber = Integer.parseInt(txtStudentNumber.getText().trim());

					boolean successAdd = library.addUser(firstName, lastName, studentNumber);
					if (successAdd)
						resetPage();
				}
			}
		});
	}
	
	// Resets the page
	public void resetPage(){
		
		requestFocus();

		for (JTextField txtField : arr_fields)
			txtField.setForeground(Visuals.gray);

		txtFirstName.setText("Only letters allowed");
		txtLastName.setText("Only letters allowed");
		txtStudentNumber.setText("Up to 6 digits allowed");
	}
}
