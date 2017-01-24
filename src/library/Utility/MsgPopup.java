package library.Utility;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import library.Main.GUI;

public class MsgPopup extends JOptionPane {

	// Displays a pop-up with the given error message
	public static void showErrorMessage(String msgText) {
		JOptionPane.showMessageDialog(null, msgText, "Uh Oh", ERROR_MESSAGE);
	}

	// Displays a pop-up with the given success message
	public static void showSuccessMessage(String msgText) {
		JOptionPane.showMessageDialog(null, msgText, "Tadaa", INFORMATION_MESSAGE);
	}

	// Displays a pop-up with the given question message
	public static int showQuestionMessage(String msgText) {
		return JOptionPane.showConfirmDialog(null, msgText, "Arer you sure?", JOptionPane.YES_NO_OPTION);
	}

	// Setting things such as font, color, etc. for all JOptionPanels
	public static void setupProperties() {

		ImageIcon warningIMG = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/library/resources/warning_50.png")));

		ImageIcon successIMG = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/library/resources/success_50.png")));

		ImageIcon questionIMG = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/library/resources/question_50.png")));

		UIManager.put("OptionPane.background", Visuals.black);
		UIManager.put("Panel.background", Visuals.black);
		UIManager.put("OptionPane.messageForeground", Visuals.white);
		UIManager.put("OptionPane.errorIcon", warningIMG);
		UIManager.put("OptionPane.informationIcon", successIMG);
		UIManager.put("OptionPane.questionIcon", questionIMG);
		UIManager.put("OptionPane.messageFont", new Font("Ubuntu", Font.PLAIN, 18));
	}

}
