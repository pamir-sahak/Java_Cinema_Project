package classes;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class is used to show every booking details made by client.
 */
public class Booking {
	// Attributes
	private String clientName;
	private String filmName;
	private String seat;
	private String date;
	private String time;
	private String phoneNumber;

	// Constructor
	public Booking(String clientName, String filmName, String seat, String date, String time, String phoneNumber) {
		this.clientName = clientName;
		this.filmName = filmName;
		this.seat = seat;
		this.date = date;
		this.time = time;
		this.phoneNumber = phoneNumber;
	}

	// Methods

	/**
	 * This method adds a new booking to file when user bought a ticket
	 * @param booking Booking
	 */
	public static void addBooking(Booking booking){
		try {
			// opening file
			FileWriter fileWriter = new FileWriter("bookings.txt", true);
			// formatting info to written
			String toWrite = booking.clientName + "," +
					booking.filmName + "," +
					booking.seat + "," +
					booking.date + "," +
					booking.time + "," +
					booking.phoneNumber;
			// writing and closing file
			fileWriter.write(toWrite+ "\n");
			fileWriter.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method returns a specific user's bookings which was sent to method as an argument
	 * @param client Client
	 * @return ArrayList<Booking>
	 */
	public static ArrayList<Booking> getClientBookings(Client client){
		ArrayList<Booking> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("bookings.txt");
			Scanner reader = new Scanner(file);

			// reading all lines from file
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] array = line.split(",");
				// checks if user name and phone number matches with booking's user in file
				// because more than on user can have same name but can not have same phone number
				if (client.getName().equals(array[0]) && client.getPhoneNumber().equals(array[5])){
					// if info matches gets booking info from formatted string
					Booking booking = new Booking(array[0], array[1], array[2], array[3], array[4], array[5]);
					list.add(booking);
				}
			}
			// closing file
			reader.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	// Getters & Setters
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
