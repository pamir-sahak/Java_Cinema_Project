package UI;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for admin's main page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class TicketController implements Initializable {
	@FXML
	Label lblName, lblFilmName, lblDate, lblSeats, lblTime, lblPrice, lblTime1, lblDate1, lblSeats1;
	@FXML
	Button btnClose;

	private Stage stage;
	private Scene scene;

	/**
	 * method that get executed while program is loaded and show user ticket information.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// showing information of ticket to user
		lblName.setText(DataHolder.getCurrentUser().getName());
		lblFilmName.setText(DataHolder.getSelectedFilmTitle());
		lblDate.setText(Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getShowDate());
		lblTime.setText(Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getStartTime());

		// just collect all seat name in a single string
		String seats = "";
		for (Seat seat : DataHolder.getSelectedSeats()){
			seats += seat.getName() + " ";
		}
		lblSeats.setText(seats);

		double amount = DataHolder.getSelectedSeats().size() * Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getPrice();
		lblDate1.setText(Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getShowDate());
		lblTime1.setText(Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getStartTime());
		lblSeats1.setText(seats);
		lblPrice.setText(amount + " $");

		// writing payment details to payment file
		Payment payment = new Payment(amount, DataHolder.getCurrentUser().getName());
		Payment.addPayment(payment);

		// Writing bookings to File
		for (Seat seat : DataHolder.getSelectedSeats()){
			Booking booking = new Booking(DataHolder.getCurrentUser().getName(),
					DataHolder.getSelectedFilmTitle(), seat.getName(),
					Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getShowDate(),
					Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()).getShow().getStartTime(),
					DataHolder.getCurrentUser().getPhoneNumber());
			Booking.addBooking(booking);
		}
	}

	/**
	 * this method just sends user to main page
	 * @param event ActionEvent
	 */
	public void gotoMainPage(ActionEvent event){
		try {
			DataHolder.setSelectedFilmTitle("");
			DataHolder.selectedSeats.clear();

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

}
