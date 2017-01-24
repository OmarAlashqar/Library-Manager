package library.Utility;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;

import library.Main.Main;

public class Visuals {

	private static String baseURL = "/library/Resources/";

	///////////////////////////////////////
	// Icon
	public static ImageIcon back = new ImageIcon(Main.class.getResource(baseURL + "back_50.png"));

	///////////////////////////////////////
	// Fonts
	public static Font headerFont = new Font("Segoe UI Black", Font.PLAIN, 40);
	public static Font listFont = new Font("Ubuntu", Font.PLAIN, 15);
	public static Font uiFont = new Font("Ubuntu", Font.PLAIN, 25);
	public static Font uiFontMedium = new Font("Ubuntu", Font.PLAIN, 20);
	public static Font uiFontBold = new Font("Ubuntu", Font.BOLD, 30);
	
	///////////////////////////////////////
	// Backgrounds
	public static ImageIcon menuBG = new ImageIcon(Main.class.getResource(baseURL + "pnlMenu.jpg"));
	public static ImageIcon addUserBG = new ImageIcon(Main.class.getResource(baseURL + "pnlAddUser.jpg"));
	public static ImageIcon addBookBG = new ImageIcon(Main.class.getResource(baseURL + "pnlAddBook.jpg"));
	public static ImageIcon deleteUserBG = new ImageIcon(Main.class.getResource(baseURL + "pnlDeleteUser.jpg"));
	public static ImageIcon deleteBookBG = new ImageIcon(Main.class.getResource(baseURL + "pnlDeleteBook.jpg"));
	public static ImageIcon checkoutBG = new ImageIcon(Main.class.getResource(baseURL + "pnlCheckoutBook.jpg"));
	public static ImageIcon returnBG = new ImageIcon(Main.class.getResource(baseURL + "pnlReturnBook.jpg"));
	public static ImageIcon lostBG = new ImageIcon(Main.class.getResource(baseURL + "pnlLostBook.jpg"));
	public static ImageIcon payFinesBG = new ImageIcon(Main.class.getResource(baseURL + "pnlPayFines.jpg"));
	public static ImageIcon searchUsersBG = new ImageIcon(Main.class.getResource(baseURL + "pnlSearchUsers.jpg"));
	public static ImageIcon searchBooksBG = new ImageIcon(Main.class.getResource(baseURL + "pnlSearchBooks.jpg"));
	public static ImageIcon detailsCoverBG = new ImageIcon(Main.class.getResource(baseURL + "detailsCover.jpg"));

	///////////////////////////////////////
	// Colors
	public static Color white = new Color(242, 242, 242);
	public static Color lBlue = new Color(40, 125, 125);
	public static Color dBlue = new Color(28, 52, 76);
	public static Color lBeige = new Color(233, 236, 202);
	public static Color dBeige = new Color(219, 218, 185);
	public static Color black = new Color(60, 60, 60);
	public static Color gray = new Color(221, 221, 221);
}
