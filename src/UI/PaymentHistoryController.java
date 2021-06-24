package UI;

import classes.DataHolder;
import classes.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * The controller for admin viewing all payments happened.
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class PaymentHistoryController implements Initializable {
	@FXML
	private TableView<Payment> table;
	@FXML
	private TableColumn<Payment, String> colID;
	@FXML
	private TableColumn<Payment, String> colName;
	@FXML
	private TableColumn<Payment, String> colAmount;
	@FXML
	private TableColumn<Payment, String> colDate;
	@FXML
	private TableColumn<Payment, String> colPaymentStatus;

	private Stage stage;
	private Scene scene;

	/**
	 * method that get executed while program is loaded
	 * and populates table with all payments information.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// adding all payments in observable list
		ObservableList<Payment> payments = FXCollections.observableArrayList(Payment.getAllPayments());
		table.setPlaceholder(new Label("No content in table"));

		/*
			specifying that which columns of table should use which field (variable)
			of every payment object.
		*/
		colName.setCellValueFactory(new PropertyValueFactory<>("payer"));
		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

		table.setItems(payments);
	}

	/***
	 * these methods sends user to new pages such as main page, remove film page and add film page
	 * @param event ActionEvent
	 */
	@FXML
	private void addFilm(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("addFilm.fxml"));
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
	private void gotoMainPage(ActionEvent event) {
		DataHolder.setSelectedFilmTitle("");
		DataHolder.selectedSeats.clear();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
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
	private void removeFilm(ActionEvent event) {
		DataHolder.setSelectedFilmTitle("");

		try {
			Parent root = FXMLLoader.load(getClass().getResource("deleteFilm.fxml"));
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
