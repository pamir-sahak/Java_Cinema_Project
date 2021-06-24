package UI;

import classes.DataHolder;
import classes.Film;
import classes.Seat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * The controller class for user paying for ticket page.
 *  * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class PaymentController implements Initializable {

	@FXML
	TextField txtKartNo, txtCashAmount, txtCCV;
	@FXML
	PasswordField txtKartPin;
	@FXML
	ComboBox<String> paymentTypeCombo, monthCombo, yearCombo;
	@FXML
	Button btnPay, btnGoToMainPage, btnSearchFilm;
	@FXML
	Label lblFilmName, lblSeats, lblTotalAmount;

	private Stage stage;
	private Scene scene;

	/***
	 * method that get executed while program is loaded
	 * and set some data for combo box such as payment type and film price, film name and seats.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// setting payment types in combo box
		ObservableList<String> payments = FXCollections.observableArrayList("Cash", "(Credit/Debit) Card");
		paymentTypeCombo.setItems(payments);

		// setting months for credit card expiration date combo box
		ObservableList<String> months = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		monthCombo.setItems(months);

		// setting years for credit card expiration date combo box
		ObservableList<String> years = FXCollections.observableArrayList("2021","2022","2023","2024","2025",
				"2026","2027","2028", "2029", "2030");
		yearCombo.setItems(years);

		txtCashAmount.setVisible(false);
		txtKartPin.setVisible(false);
		txtCCV.setVisible(false);
		txtKartNo.setVisible(false);
		monthCombo.setVisible(false);
		yearCombo.setVisible(false);

		// showing user the booking info such as price, film name and etc
		lblFilmName.setText("Film: " + DataHolder.getSelectedFilmTitle());
		double totalAmount = Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getPrice() * DataHolder.getSelectedSeats().size();
		lblTotalAmount.setText("Total Amount: " + totalAmount + "$");

		// Just taking names of all Booked seat and adding ',' at the end of seat name
		String seats = "";
		int count = DataHolder.getSelectedSeats().size() - 1;
		for (Seat seat : DataHolder.getSelectedSeats()){
			seats += seat.getName();
			if (count > 0){
				seats += ",";
				count--;
			}
		}
		lblSeats.setText("Seats: " + seats);

	}

	/***
	 * This methods shows required text or combo boxes according to selected payment type (Cash / Card)
	 */
	@FXML
	private void paymentTypeSelection(){
		String paymentType = paymentTypeCombo.getSelectionModel().getSelectedItem();

		// showing cash payment text field
		if (paymentType.equals("Cash")){
			txtKartNo.setVisible(false);
			txtKartPin.setVisible(false);
			txtCCV.setVisible(false);
			yearCombo.setVisible(false);
			monthCombo.setVisible(false);
			txtCashAmount.setVisible(true);
		}

		// showing Card payment text fields and combo boxes
		else {
			txtCashAmount.setVisible(false);
			txtKartNo.setVisible(true);
			txtKartPin.setVisible(true);
			txtCCV.setVisible(true);
			yearCombo.setVisible(true);
			monthCombo.setVisible(true);

		}
	}

	/**
	 * this method is called when pay button is pressed and it will call other methods
	 * if user selected cash payment type payInCash() method is called
	 * if user selected Credit card payment type payWithCard() method is called
	 * @param event ActionEvent
	 */
	@FXML
	private void pay(ActionEvent event){
		// showing warning if payment type is not selected
		if (paymentTypeCombo.getSelectionModel().getSelectedItem() == null){
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select Payment Type");
			alert.show();
		}

		// calling other methods according to payment type
		else {
			String paymentType = paymentTypeCombo.getSelectionModel().getSelectedItem();

			if (paymentType.equals("Cash")) {
				payInCash(event);
			} else {
				payWithCard(event);
			}
		}
	}

	/**
	 * This method controls user's payment in cash
	 * @param event ActionEvent
	 */
	private void payInCash(ActionEvent event) {
		try {
			// if total amount is equal to amount that entered by user in text box
			// it take payment successfully and show user his ticket
			if ((Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getPrice() * DataHolder.getSelectedSeats().size()) == Double.parseDouble(txtCashAmount.getText())) {
				Parent root = FXMLLoader.load(getClass().getResource("ticket.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			// showing warning when user entered different amount than total amount
			else if ((Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getPrice() * DataHolder.getSelectedSeats().size()) != Double.parseDouble(txtCashAmount.getText())) {
				String message = "Ticket Price is: " + Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getPrice() +
						"\nPlease Enter Correct price";
				Alert alert = new Alert(Alert.AlertType.WARNING, message);
				alert.show();
			}
		} catch (Exception ignored) {
		}
	}

	/**
	 * This method controls user's payment with credit card
	 * @param event ActionEvent
	 */
	private void payWithCard(ActionEvent event){
		// showing warning if all required info is not selected or entered
		if (txtKartNo.getText().equals("") || txtCCV.getText().equals("") ||
			txtKartPin.getText().equals("") || yearCombo.getSelectionModel().getSelectedItem() == null ||
			monthCombo.getSelectionModel().getSelectedItem() == null){
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all required information");
			alert.show();
		}
		else {
			// card number must be 16 characters.
			// user that entered 16 chars in card number text field is good and ticket will be showed
			if (txtKartNo.getText().length() == 16) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("ticket.fxml"));
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// showing warning if card number is less than 16 characters
			else {
				Alert alert = new Alert(Alert.AlertType.WARNING, "Card Number Must be 16 Digits");
				alert.show();
			}
		}
	}

	/***
	 *these methods sends user to new pages such as main page or search film page
	 * @param event ActionEvent
	 */
	@FXML
	private void gotoMainPage(ActionEvent event){
		DataHolder.setSelectedFilmTitle("");
		DataHolder.selectedSeats.clear();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("showfilms.fxml"));
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@FXML
	private void searchFilm(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("searchFilm.fxml"));
			stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
