package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a class that represents seats in cinema salon.
 */
public class Seat {
	// Attributes
	private boolean booked = false;
	private String name;

	// Constructor
	public Seat(String name) {
		this.name = name;
	}

	// Methods

	/**
	 * This method returns all booked seats for a specific film which name was sent as argument to method
	 * @param filmName String
	 * @return ArrayList<Seat>
	 */
	public static ArrayList<Seat> getBookedSeats(String filmName) {
		ArrayList<Seat> list = new ArrayList<>();
		try {
			// opening file
			File file = new File("bookings.txt");
			Scanner reader = new Scanner(file);

			// reading all lines from file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// checks if film names are matching than seat will added to arraylist.
				if (filmName.toLowerCase().equals(array[1].toLowerCase())) {
					Seat seat = new Seat(array[2]);
					list.add(seat);
				}
			}
			// closing file
			reader.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Getters & Setters
	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
