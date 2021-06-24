package classes;

/**
 * this class is created to be used by Person class.
 * it represents each user's password, user name and email.
 */
public class Account {
	// Attributes
	private String userName;
	private String email;
	private String password;

	// Constructor
	public Account(String userName, String password, String email){
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	// Getters & Setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
