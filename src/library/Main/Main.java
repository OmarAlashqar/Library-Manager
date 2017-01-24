package library.Main;

import library.Objects.Library;
import library.Utility.MsgPopup;

public class Main {

	public static void main(String[] args) {

		// Initializing a new library system
		Library library = new Library();
		
		// Initializing an application window
		GUI gui = new GUI(library);
	}
}
