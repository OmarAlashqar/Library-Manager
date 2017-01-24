package library.Utility;

import java.text.DateFormatSymbols;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Kit {

	// Returns the input after capitalizing each word in the input
	public static String correctCapitalization(String original) {
		String capitalized = "";

		// Initializing an array of strings from the original
		String[] arr_str = original.split(" ");
		for (String word : arr_str)
			capitalized += capitalize(word) + " ";

		capitalized = capitalized.trim();
		return capitalized;
	}

	// Returns the string (one word) capitalized
	public static String capitalize(String original) {
		String capitalized;
		original = original.toLowerCase();
		capitalized = original.substring(0, 1).toUpperCase() + original.substring(1);
		return capitalized;
	}
	
	// Returns a nicely formatted string based on date inputted
	public static String formatDate(ZonedDateTime date){
		String formattedDate = "";
		
		String[] monthNames = new DateFormatSymbols().getMonths();
		
		formattedDate += monthNames[date.getMonthValue() - 1] + " ";
		formattedDate += date.getDayOfMonth() + ", ";
		formattedDate += date.getYear();
		
		return formattedDate;
	}

	// Sets up basic properties for a text field
	public static void setupField(JTextField txtField) {
		txtField.setFont(Visuals.uiFontMedium);
		txtField.setBackground(Visuals.white);
		txtField.setForeground(Visuals.gray);
		txtField.setBorder(new LineBorder(Visuals.white, 5));
		txtField.setColumns(10);
	}

	// Sets up basic properties for a text field
	public static void setupCombobox(JComboBox comboBox) {
		((JLabel) comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboBox.setFont(Visuals.uiFontMedium);
		comboBox.setBackground(Visuals.white);
		comboBox.setForeground(Visuals.black);
	}

	// Sets up basic properties for a text field
	public static void setupList(JList list) {
		list.setForeground(Visuals.black);
		list.setFont(Visuals.listFont);
		list.setBackground(Visuals.white);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// Returns true if the forms have all been filled out properly
	public static boolean checkForm(ArrayList<JTextField> arr_fields) {
		// Checking if any of the text fields still has the default color
		for (JTextField txtField : arr_fields) {
			if (txtField.getForeground().equals(Visuals.gray))
				return false;
		}
		return true;
	}

}
