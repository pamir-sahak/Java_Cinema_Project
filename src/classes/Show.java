package classes;

/**
 * this class represents show details of each film
 * this class is used by Film Class
 */
public class Show {
	// Attributes
	private String showDate;
	private String startTime;
	private String endTime;

	// Constructor
	public Show(String showDate, String startTime, String endTime) {
		this.showDate = showDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Getters & Setters
	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
