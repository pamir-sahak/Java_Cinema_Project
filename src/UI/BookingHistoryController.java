package UI;

import classes.Booking;
import classes.Client;
import classes.DataHolder;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * The controller for user viewing his tickets page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class BookingHistoryController implements Initializable {
	@FXML
	private TableView<Booking> table;
	@FXML
	private TableColumn<Booking, String> colName;
	@FXML
	private TableColumn<Booking, String>colFilmName;
	@FXML
	private TableColumn<Booking, String>colSeat;
	@FXML
	private TableColumn<Booking, String>colDate;
	@FXML
	private TableColumn<Booking, String>colTime;
	@FXML
	Button btnGoToMainMenu, btnGoBack, btnSearchFilm, btnBuyTicket;

	private Stage stage;
	private Scene scene;

	/**
	 * method that get executed while program is loaded.
	 * and populates table with all book information that current user did.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// reading all booking information
		ObservableList<Booking> bookings = FXCollections.observableArrayList(Booking.getClientBookings((Client)DataHolder.getCurrentUser()));

		table.setPlaceholder(new Label("No Content Found!!"));
		/*
			specifying that which columns of table should use which field (variable)
			of every booking object.
		*/
		colName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
		colFilmName.setCellValueFactory(new PropertyValueFactory<>("filmName"));
		colSeat.setCellValueFactory(new PropertyValueFactory<>("seat"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

		table.setItems(bookings);
	}

	/***
	 * these methods sends user to new pages such as main page, buy film ticket page and search film page.
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
	private void buyTicket(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("buyTicket.fxml"));
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
