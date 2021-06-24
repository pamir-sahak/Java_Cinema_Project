package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents general user admin and user in application to act like parent
 * it will be inherited by Admin and Client classes
 */
public class Person {
	// Attributes
	private String name;
	private String email;
	private String phoneNumber;
	private Account account;

	// Constructors
	public Person(String name, String email, String phoneNumber, Account account) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.account = account;
	}


	// Methods

	/**
	 * This method returns all users from file contains admins and clients as an ArrayList.
	 * @return ArrayList<Person>
	 */
	public static ArrayList<Person> readAllUsers(){
		ArrayList<Person> list = new ArrayList<>();

		try {
			// opening admin file
			File file = new File("admins.txt");
			Scanner reader = new Scanner(file);

			// reading all lines from admin file
			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] array = line.split(",");
				// getting admin info from formatted string
				Admin admin = new Admin(array[0],array[1], array[2], new Account(array[0], array[3], array[1]));
				list.add(admin);
			}
			// closing admin file
			reader.close();

			// opening client file
			File file2 = new File("clients.txt");
			Scanner reader2 = new Scanner(file2);

			// reading all lines from client file
			while (reader2.hasNextLine()){
				String line = reader2.nextLine();
				String[] array = line.split(",");
				// getting client info from formatted string
				Client client = new Client(array[0],array[1], array[2], new Account(array[0], array[3], array[1]));
				list.add(client);
			}
			// closing client file
			reader2.close();
		}

		catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * this method returns the user type in general.
	 * @return String
	 */
	public String getType(){
		return "user";
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
