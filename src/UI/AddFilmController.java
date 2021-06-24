package UI;

import classes.CinemaSalon;
import classes.DataHolder;
import classes.Film;
import classes.Show;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/***
 * The controller for admin adding film page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class AddFilmController implements Initializable {

	@FXML
	private TextField txtFilmName;
	@FXML
	private TextField txtFilmLanguage;
	@FXML
	private ComboBox<String> filmTypeCombo;
	@FXML
	private TextField txtFilmPrice;
	@FXML
	private TextField txtFilmReleasedDate;
	@FXML
	private DatePicker filmShowDate;
	@FXML
	private ComboBox<String> filmStartTimeCombo;
	@FXML
	private ComboBox<String> filmEndTimeCombo;
	@FXML
	private ComboBox<String> salonCombo;

	private Stage stage;
	private Scene scene;
	private File selectedImage;

	/**
	 * method that get executed while program is loaded.
	 * and set some data for combo boxes such as film types, start and end time and etc.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// storing elements in the observable list
		ObservableList<String> filmTypes = FXCollections.observableArrayList("Action", "Animation", "Documentary",
				"Biography", "Comedy", "Romantic", "Religious");

		// storing elements in combo box
		ObservableList<String> startTimes = FXCollections.observableArrayList("10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30",
				"15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00");

		// storing elements in the observable list
		ObservableList<String> endTimes = FXCollections.observableArrayList("10:00", "10:30", "11:00", "11:30",
				"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
				"15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
				"19:00", "19:30", "20:00");

		// storing elements in the observable list
		ObservableList<String> salons = FXCollections.observableArrayList("Hall A", "Hall B", "Hall C",
				"Hall D", "Hall E");

		// adding list data to combo boxes
		filmTypeCombo.setItems(filmTypes);
		filmEndTimeCombo.setItems(endTimes);
		filmStartTimeCombo.setItems(startTimes);
		salonCombo.setItems(salons);
	}

	/***
	 * This method performs the image adding task while adding new film
	 */
	@FXML
	private void uploadImage() {
		// checking that input file is not null and handling the exception
		try {
			FileChooser fileChooser = new FileChooser();
			selectedImage = fileChooser.showOpenDialog(null);

			// if admin adds no image there will a default poster added
			if (selectedImage == null)
				selectedImage = new File("..\\images\\defaultFilmPoster.png");

				// handling unsupported format files
			else if (ImageIO.read(selectedImage) == null) {
				Alert alert = new Alert(Alert.AlertType.WARNING, "Please select .jpg or .png File only",
						ButtonType.OK);
				alert.showAndWait();
				if (alert.getResult() == ButtonType.OK) {
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/***
	 * This method adds new film and saves it's information in films.txt file
	 */
	@FXML
	private void addFilm() {

		// Checking that all need info to create a film is entered by user
		if (txtFilmName.getText().equals("") || txtFilmLanguage.getText().equals("") ||
				txtFilmPrice.getText().equals("") || txtFilmReleasedDate.getText().equals("") ||
				filmShowDate.getValue() == null || filmStartTimeCombo.getSelectionModel().getSelectedItem() == null ||
				filmEndTimeCombo.getSelectionModel().getSelectedItem() == null ||
				filmTypeCombo.getSelectionModel().getSelectedItem() == null ||
				salonCombo.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill all required information");
			alert.show();
		}

		// Checking that Film Price should be only Numbers
		else if (!isNumeric(txtFilmPrice.getText())) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter digit only in film Price Text Box");
			alert.show();
		}

		// Checking that Film Released year should be only Numbers like(2018)
		else if (!isNumeric(txtFilmReleasedDate.getText())) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter film released date only ex:(2018)");
			alert.show();
		}

		// all the required info is entered by user
		// info is getting ready for film to be added
		else {
			String imgSrc;

			// adding default poster as film image path if image is not specified
			if (selectedImage == null) {
				imgSrc = "..\\images\\defaultFilmPoster.png";
			}

			// changing '\' character to '/' character in image path
			else {
				imgSrc = selectedImage.getAbsolutePath();
				imgSrc = imgSrc.replace("\\", "/");
			}

			// formatting film show date in (01-01-2021) format
			LocalDate date = filmShowDate.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String showDate = date.format(formatter);

			// creating new film
			Film film = new Film(
					txtFilmName.getText(),
					txtFilmLanguage.getText(),
					filmTypeCombo.getSelectionModel().getSelectedItem(),
					Integer.valueOf(txtFilmReleasedDate.getText()),
					Double.valueOf(txtFilmPrice.getText()),
					imgSrc,
					new Show(showDate, filmStartTimeCombo.getSelectionModel().getSelectedItem(),
							filmEndTimeCombo.getSelectionModel().getSelectedItem()),
					new CinemaSalon(salonCombo.getSelectionModel().getSelectedItem()));

			// validating film: controlling that film not exists already or film's show
			// date or time is not same with other films in system
			if (Film.validateFilmForAddition(film)) {
				Film.addFilm(film);
				Alert alert = new Alert(Alert.AlertType.INFORMATION, film.getName() + " Added successfully");
				alert.show();
			}
		}
	}

	/***
	 * these methods sends user to new pages such as main page, remove film page and payment history page
	 * @param event ActionEvent
	 */
	@FXML
	private void gotoMainPage(ActionEvent event) {
		DataHolder.setSelectedFilmTitle("");
		DataHolder.selectedSeats.clear();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void removeFilm(ActionEvent event) {
		DataHolder.setSelectedFilmTitle("");

		try {
			Parent root = FXMLLoader.load(getClass().getResource("deleteFilm.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void paymentHistory(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("paymentHistory.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * this method check film price and released year to be numeric
	 * @param str String
	 * @return boolean
	 */
	private boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}