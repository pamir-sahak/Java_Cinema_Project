package classes;

/**
 * this classed is used to specify seats in a cinema salon.
 */
public class CinemaSalon {
	// Attributes
	private String Name;

	// Constructor
	public CinemaSalon(String name) {
		Name = name;
	}

	// Getters & Setters
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
