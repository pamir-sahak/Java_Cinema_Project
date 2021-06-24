package classes;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * this class is responsible for taking payments add saving them to file.
 */
public class Payment {
	// Attributes
	private int id;
	private double amount;
	private String paymentDate;
	private String paymentStatus;
	private String payer;

	// Constructor used while adding payment
	public Payment(double amount, String payer) {
		Random random = new Random();
		LocalDate unformattedDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = unformattedDate.format(formatter);

		this.id = random.nextInt(99999) + 555;
		this.amount = amount;
		this.paymentDate = date;
		this.paymentStatus = "Confirmed";
		this.payer = payer;
	}

	// second Constructor used while reading payments from file
	public Payment(int id, String payer, double amount, String paymentDate,  String paymentStatus) {
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.payer = payer;
	}

	// Methods

	/**
	 * this method returns all payments from file as an ArrayList.
	 * @return ArrayList<Payment>
	 */
	public static ArrayList<Payment> getAllPayments(){
		ArrayList<Payment> list = new ArrayList<>();

		try {
			// opening file
			File file = new File("payments.txt");
			Scanner reader = new Scanner(file);

			// reading all lines from file
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] array = line.split(",");
				// getting payment info from formatted string
				Payment payment = new Payment(Integer.parseInt(array[0]),array[1],
						Double.parseDouble(array[2]), array[3], array[4]);
				list.add(payment);
			}
			// closing file
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * This method add a single payment information to file when user buys ticket.
	 * @param payment Payment
	 */
	public static void addPayment(Payment payment){
		try {
			// formatting payment info to be written to file
			String toWrite = payment.getId() + "," +
					payment.getPayer() + "," +
					payment.getAmount() + "," +
					payment.getPaymentDate() + "," +
					payment.getPaymentStatus();
			// opening file and writing info
			FileWriter fileWriter = new FileWriter("payments.txt", true);
			fileWriter.write(toWrite + "\n");
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}
}
