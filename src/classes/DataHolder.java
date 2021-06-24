package classes;

import java.util.ArrayList;

/**
 * this class is created to hold temporary data while application is running
 * such as current user, selected film and selected seats
 * and data will be erased when application is closed
 */
public class DataHolder {
	// Attributes
	private static Person currentUser;
	private static String selectedFilmTitle = "";
	public static ArrayList<Seat> selectedSeats = new ArrayList<>();

	// Getters & Setters
	public static Person getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(Person currentUser) {
		DataHolder.currentUser = currentUser;
	}

	public static String getSelectedFilmTitle() {
		return selectedFilmTitle;
	}

	public static void setSelectedFilmTitle(String selectedFilmTitle) {
		DataHolder.selectedFilmTitle = selectedFilmTitle;
	}

	public static ArrayList<Seat> getSelectedSeats() {
		return selectedSeats;
	}

	public static void setSelectedSeats(ArrayList<Seat> selectedSeats) {
		DataHolder.selectedSeats = selectedSeats;
	}
}
