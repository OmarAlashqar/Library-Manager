package library.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import library.Objects.Book;
import library.Objects.Library;
import library.Objects.User;
import library.Utility.Kit;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class LookupUsersPanel extends JPanel {

	private Library library;
	private JList lstUsers;
	private JScrollPane scrlUsers;
	private JButton btnBackToMenu;
	private JScrollPane scrlCheckedOut;
	private JList lstCheckedOut;
	private JLabel lblName;
	private JLabel lblStudentNumber;
	private JLabel lblFines;
	private JLabel lblBG;
	private JLabel lblDetailsCover;

	private DefaultListModel<User> usersModel = new DefaultListModel<User>();
	private DefaultListModel<Book> booksModel = new DefaultListModel<Book>();

	public LookupUsersPanel(Library library) {
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

		lstUsers = new JList();
		lstUsers.setBorder(null);
		Kit.setupList(lstUsers);

		scrlUsers = new JScrollPane();
		scrlUsers.setBorder(null);
		scrlUsers.setBounds(85, 156, 231, 388);
		scrlUsers.setViewportView(lstUsers);
		
		lstCheckedOut = new JList();
		lstCheckedOut.setBorder(null);
		Kit.setupList(lstCheckedOut);
		
		scrlCheckedOut = new JScrollPane();
		scrlCheckedOut.setBorder(null);
		scrlCheckedOut.setBounds(402, 334, 392, 91);
		scrlCheckedOut.setViewportView(lstCheckedOut);
		
		lblName = new JLabel();
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(Visuals.uiFontBold);
		lblName.setForeground(Visuals.white);
		lblName.setBounds(402, 118, 392, 66);
		
		lblStudentNumber = new JLabel();
		lblStudentNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentNumber.setForeground(new Color(242, 242, 242));
		lblStudentNumber.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		lblStudentNumber.setBounds(402, 185, 392, 66);

		lblFines = new JLabel();
		lblFines.setForeground(Visuals.gray);
		lblFines.setFont(Visuals.uiFontBold);
		lblFines.setBounds(642, 487, 119, 52);
		
		lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.searchUsersBG);
		
		lblDetailsCover = new JLabel();
		lblDetailsCover.setBounds(402, 89, 395, 478);
		lblDetailsCover.setIcon(Visuals.detailsCoverBG);
		
		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(lblDetailsCover);
		add(scrlUsers);
		add(btnBackToMenu);
		add(scrlCheckedOut);
		add(lblName);
		add(lblStudentNumber);
		add(lblFines);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		addComponentListener(new ComponentAdapter() {
			// This will run every time this page is loaded
			public void componentShown(ComponentEvent arg0) {
				setUsersModel();
				lblDetailsCover.setVisible(true);
			}
		});

		lstUsers.addListSelectionListener(new ListSelectionListener() {
			// This will run every time a new user is selected from the list
			public void valueChanged(ListSelectionEvent e) {

				// If a user is selected
				if (lstUsers.getSelectedIndex() != -1) {
					User user = (User) lstUsers.getSelectedValue();

					refreshBooksList(user);
					lblName.setText(user.getFullName());
					lblStudentNumber.setText(user.getStudentNum() + "");
					lblFines.setText(user.getFinesDue() + "");
					
					lblDetailsCover.setVisible(false);
				}
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

		// Setting a new renderer so that the objects in the booksModel are
		// printed nicely in the list
		lstCheckedOut.setCellRenderer(new DefaultListCellRenderer() {
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
				lstUsers.clearSelection();

				lblName.setText("");
				lblStudentNumber.setText("");
				lblFines.setText("");
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

	// This will refresh the books checkout out list
	public void refreshBooksList(User user) {

		// Clearing out the current model
		booksModel.clear();

		// Adding all the current users to the model
		for (Book book : user.getBooksCheckedOut())
			booksModel.addElement(book);

		lstCheckedOut.setModel(booksModel);
	}
}
