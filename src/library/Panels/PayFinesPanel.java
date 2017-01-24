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

import library.Objects.Library;
import library.Objects.User;
import library.Utility.Kit;
import library.Utility.MsgPopup;
import library.Utility.PANEL;
import library.Utility.Visuals;

public class PayFinesPanel extends JPanel{
	
	private Library library;
	private JList lstUsers;
	private JButton btnClearFines;
	private JScrollPane scrollPane;
	private JButton btnBackToMenu;

	private DefaultListModel<User> usersModel = new DefaultListModel<User>();

	public PayFinesPanel(Library library) {
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

		btnClearFines = new JButton("Clear Fines");
		btnClearFines.setBounds(444, 256, 288, 109);
		btnClearFines.setForeground(Visuals.dBlue);
		btnClearFines.setFont(Visuals.uiFontBold);
		btnClearFines.setFocusable(false);
		btnClearFines.setBackground(Visuals.lBeige);

		lstUsers = new JList();
		lstUsers.setBorder(null);
		Kit.setupList(lstUsers);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(85, 156, 231, 388);
		scrollPane.setViewportView(lstUsers);

		JLabel lblBG = new JLabel();
		lblBG.setBounds(0, 0, 797, 567);
		lblBG.setIcon(Visuals.payFinesBG);

		///////////////////////////////////////////////////////////////
		// Setting up the whole panel and adding the components

		setLayout(null);
		add(btnClearFines);
		add(scrollPane);
		add(btnBackToMenu);
		add(lblBG);
	}

	// All the ActionListeners for components will be placed here
	public void createEvents() {

		addComponentListener(new ComponentAdapter() {
			// This will run every time this page is loaded
			public void componentShown(ComponentEvent arg0) {
				setUsersModel();
			}
		});

		// Setting a new renderer so that the objects in the userModel are
		// printed nicely in the list
		lstUsers.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				User user = (User) value;
				((JLabel) renderer).setText("  " + (user.getBasicDetails() + " ($" + user.getFinesDue() + ")"));
				return renderer;
			}
		});

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				library.display(PANEL.menu);
			}
		});

		btnClearFines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// If something is selected
				if (!lstUsers.isSelectionEmpty()) {
					User user = (User) lstUsers.getSelectedValue();

					user.clearFines();

				} else
					MsgPopup.showErrorMessage("Make sure you selected a user!");
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
}
