package UI;

import classes.DataHolder;
import classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * The controller for admin adding film page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class SelectSeatController implements Initializable{
	@FXML
	CheckBox A1, A2, A3, A4, B1, B2, B3, B4, C1, C2, C3, C4, D1, D2, D3, D4;
	@FXML
	ImageView A1img, A2img, A3img, A4img, B1img, B2img, B3img, B4img, C1img, C2img, C3img, C4img, D1img, D2img, D3img, D4img;
	@FXML
	Button btnPay, btnGoToMainPage;

	private Stage stage;
	private Scene scene;

	private ArrayList<CheckBox> allSeats = new ArrayList<>();
	private ArrayList<CheckBox> alreadyBookedSeats = new ArrayList<>();
	private ArrayList<Seat> bookedSeats = new ArrayList<>();
	private ArrayList<Seat> temp = new ArrayList<>();
	private ArrayList<ImageView> allImages = new ArrayList<>();

	/**
	 * method that get executed while program is loaded.
	 * setting already booked seats disabled and showing all seats in cinema salon.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// adding all seats
		allSeats.add(A1);
		allSeats.add(A2);
		allSeats.add(A3);
		allSeats.add(A4);
		allSeats.add(B1);
		allSeats.add(B2);
		allSeats.add(B3);
		allSeats.add(B4);
		allSeats.add(C1);
		allSeats.add(C2);
		allSeats.add(C3);
		allSeats.add(C4);
		allSeats.add(D1);
		allSeats.add(D2);
		allSeats.add(D3);
		allSeats.add(D4);

		// adding image for all seats
		allImages.add(A1img);
		allImages.add(A2img);
		allImages.add(A3img);
		allImages.add(A4img);
		allImages.add(B1img);
		allImages.add(B2img);
		allImages.add(B3img);
		allImages.add(B4img);
		allImages.add(C1img);
		allImages.add(C2img);
		allImages.add(C3img);
		allImages.add(C4img);
		allImages.add(D1img);
		allImages.add(D2img);
		allImages.add(D3img);
		allImages.add(D4img);

		// getting already booked seats as seat object
		temp = Seat.getBookedSeats(DataHolder.getSelectedFilmTitle());

		 // adding check boxes to already booked seats
		// if seat name matches to check box name
		for (CheckBox seat : allSeats){
			for (Seat bookedSeat : temp){
				if (bookedSeat.getName().equals(seat.getId())){
					alreadyBookedSeats.add(seat);
				}
			}
		}

		// disabling check box and it's image for already booked seats
		for (CheckBox checkBox : alreadyBookedSeats){
			String imgID = checkBox.getId() + "img";
			checkBox.setDisable(true);
			for (ImageView imageView : allImages){
				if (imageView.getId().equals(imgID)){
					Image image = new Image(".\\icons\\armchairRed.png");
					imageView.setImage(image);
					break;
				}
			}
		}

	}

	/**
	 * this method is calling selection and deselection method while user is selecting seats
	 * @param event ActionEvent
	 */
	@FXML
	private void selection(ActionEvent event){
		// if check box is selected
		if (((CheckBox) event.getSource()).isSelected()) {
			selectSeat(event);
		}
		// if check box selection is removed
		if (!((CheckBox) event.getSource()).isSelected()) {
			deSelectSeat(event);
		}
	}

	/**
	 * This method is removing seat from selection
	 * @param event ActionEvent
	 */
	private void deSelectSeat(ActionEvent event) {
		String id = ((CheckBox) event.getSource()).getId();
		String imageID = id+"img";
		for (ImageView imageView : allImages) {
			// if user removed selection of check box
			if (imageView.getId().equals(imageID)) {
				for (Seat seat : bookedSeats) {
					// seat will be removed from booked seat
					if (seat.getName().equals(id)) {
						bookedSeats.remove(seat);
						// and changes it's image to normal so it can be selected
						Image image = new Image(".\\icons\\armchair.png");
						imageView.setImage(image);
						break;
					}
				}
			}
		}
	}

	/**
	 * This method is selecting seat.
	 * @param event ActionEvent
	 */
	public void selectSeat(ActionEvent event){
		String id = ((CheckBox) event.getSource()).getId();
		String imageID = id+"img";
		for (ImageView imageView : allImages) {
			// if user check the check box
			if (imageView.getId().equals(imageID)) {
				// seat is added to booked seats
				Seat seat = new Seat(id);
				bookedSeats.add(seat);
				// and it's color becomes red so it can not be selected
				Image image = new Image(".\\icons\\armchairGreen.png");
				imageView.setImage(image);
			}
		}
	}

	/***
	 * these methods sends user to new pages such as main page and paying for ticket page.
	 * @param event ActionEvent
	 */
	@FXML
	private void pay(ActionEvent event){
		if (bookedSeats.size() == 0){
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select Seat");
			alert.show();
		}
		else {
			try {
				DataHolder.setSelectedSeats(bookedSeats);

				Parent root = FXMLLoader.load(getClass().getResource("payment.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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

}
