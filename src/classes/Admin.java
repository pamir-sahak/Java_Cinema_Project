package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class defines a new admin user for application.
 *  it inherits Person class
 */
public class Admin extends Person{

	// Constructor
	public Admin(String name, String email, String phoneNumber, Account account) {
		super(name, email, phoneNumber, account);
	}

	/**
	 * this method returns all admin user as an ArrayList which is saved in file.
	 * @return ArrayList<admin>
	 */
	public static ArrayList<Admin> readAdmins(){
		ArrayList<Admin> list = new ArrayList<>();

		try {
			File file = new File("admins.txt");
			Scanner reader = new Scanner(file);

			while (reader.hasNextLine()){
				String line = reader.nextLine();
				String[] array = line.split(",");
				// getting user info from formatted string
				Admin admin = new Admin(array[0],array[1], array[2], new Account(array[0], array[3], array[1]));
				list.add(admin);
			}
			reader.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method returns user type
	 * @return String
	 */
	@Override
	public String getType(){
		return "admin";
	}

}
