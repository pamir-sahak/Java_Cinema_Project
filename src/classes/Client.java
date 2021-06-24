package classes;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class defines a new client user for application.
 * it inherits Person class
 */
public class Client extends Person {
	// Constructor
	public Client(String name, String email, String phoneNumber, Account account) {
		super(name, email, phoneNumber, account);
	}

	// Methods
	/**
	 * this method adds a new user to system and saves it to file
	 * @param userName String
	 * @param email String
	 * @param telNo String
	 * @param password String
	 * @param password2 String
	 */
	public static void addClient(String userName, String email, String telNo, String password, String password2) {
		Client client = new Client(userName, email, telNo, new Account(userName, password, email));

		boolean doesExist = false;
		ArrayList<Client> allClients = Client.readClients();

		// checking if entered phone number or email is already used by another user
		for (Client c : allClients) {
			if (client.getPhoneNumber().equals(c.getPhoneNumber()) ||
					client.getEmail().equals(c.getEmail())) {
				doesExist = true;
				break;
			}
		}

		try {
			// if email or phone number is not used already then it will add user
			if (!doesExist) {
				// checking that all required information is not empty
				if (client.getName().equals("") ||
						client.getEmail().equals("") ||
						client.getPhoneNumber().equals("") ||
						client.getAccount().getPassword().equals("") ||
						password2.equals("")) {

					// showing warning if info to crate user is missing
					Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all required information");
					alert.show();
				}
				// checking that password is same at both password fields
				else if (!password.equals(password2)) {
					Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter the same password twice");
					alert.show();
				}
				// ',' comma character is forbidden to use
				// so checking that no information contains comma.
				else if (client.getName().contains(",") ||
						client.getEmail().contains(",") ||
						client.getPhoneNumber().contains(",") ||
						client.getAccount().getPassword().contains(",")) {
					Alert alert = new Alert(Alert.AlertType.WARNING,
							"',' comma character can not be use in user name, password, email or phone number");
					alert.show();
				}
				// everything is good and user will be added to file
				else {
					// formatting user info to be added to file
					String toWrite = client.getName() + "," +
							client.getEmail() + "," +
							client.getPhoneNumber() + "," +
							client.getAccount().getPassword();
					FileWriter fileWriter = new FileWriter("clients.txt", true);
					fileWriter.write(toWrite + "\n");
					fileWriter.close();
					Alert alert = new Alert(Alert.AlertType.INFORMATION, "User added successfully");
					alert.show();
				}
			}
			// if phone number of email is already exists showing warning
			if (doesExist) {
				Alert alert = new Alert(Alert.AlertType.WARNING, "Entered phone number or email is already used");
				alert.show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method returns all admin user as an ArrayList which is saved in file.
	 * @return ArrayList<Client>
	 */
	public static ArrayList<Client> readClients() {
		ArrayList<Client> list = new ArrayList<>();

		try {
			File file = new File("clients.txt");
			Scanner reader = new Scanner(file);

			// reading all lines from file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// getting user info from formatted string
				Client client = new Client(array[0], array[1], array[2], new Account(array[0], array[3], array[1]));
				list.add(client);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method returns user type
	 * @return String
	 */
	@Override
	public String getType() {
		return "client";
	}
}
