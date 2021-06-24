package UI;

import classes.DataHolder;
import classes.Film;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for showing selected film information.
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class showSelectedFilmController implements Initializable {
	Film selectedFilm = null;
	@FXML
	Button btnGoToMainPage, btnSearchFilm, btnGoBack, btnSelectSeats, btnDeleteFilm, btnAddFilm, btnPayments;
	@FXML
	Text filmStartTime, filmEndTime, filmShowDate, filmLanguage, filmType, filmReleasedDate, filmSalon;
	@FXML
	Label filmName;
	@FXML
	ImageView img;
	@FXML
	Label filmPrice;

	private Stage stage;
	private Scene scene;

	/**
	 * method that get executed while program is loaded.
	 * sets selected film info such as film name, film language, type, price and etc.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// disabling some button for client user
		if (DataHolder.getCurrentUser().getType().equals("client")){
			btnDeleteFilm.setVisible(false);
			btnSelectSeats.setVisible(true);
			btnAddFilm.setVisible(false);
			btnSearchFilm.setVisible(true);
			btnPayments.setVisible(false);
		}
		// disabling some button for admin user
		else {
			btnSelectSeats.setVisible(false);
			btnDeleteFilm.setVisible(true);
			btnAddFilm.setVisible(true);
			btnSearchFilm.setVisible(false);
			btnPayments.setVisible(true);
		}
		// getting selected film info
		selectedFilm = Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle());

		// showing selected film info to user
		filmName.setText(selectedFilm.getName());
		filmType.setText(selectedFilm.getType());
		filmLanguage.setText(selectedFilm.getLanguage());
		filmReleasedDate.setText(selectedFilm.getReleasedDate()+ "");
		filmStartTime.setText(selectedFilm.getShow().getStartTime());
		filmEndTime.setText(selectedFilm.getShow().getEndTime());
		filmShowDate.setText(selectedFilm.getShow().getShowDate());
		filmSalon.setText(selectedFilm.getSalon().getName());

		filmPrice.setText(selectedFilm.getPrice()+ "$");


		try {
			// getting film image path if path is relative
			InputStream file = getClass().getResourceAsStream(selectedFilm.getImgSrc());
			if (file == null) {
				// getting film image path if is absolute.
				file = new FileInputStream(selectedFilm.getImgSrc());
			}

			// showing film image
			Image image = new Image(file);
			img.setImage(image);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * this method is letting admin user to delete film
	 * @param event ActionEvent
	 */
	@FXML
	private void deleteFilm(ActionEvent event){
		// showing warning before deleting film
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, DataHolder.getSelectedFilmTitle()+ " Film will be deleted\nAre you sure?");
		alert.showAndWait();

		// if button OK is clicked film will be deleted
		if (alert.getResult() == ButtonType.OK){

			Film.deleteFilm(Film.returnSelectedFilm(DataHolder.getSelectedFilmTitle()));

			Alert alert2 = new Alert(Alert.AlertType.INFORMATION, DataHolder.getSelectedFilmTitle() + " Film Deleted Successfully");
			alert2.show();
			DataHolder.setSelectedFilmTitle("");

			// goes to main page
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
	}

	/***
	 * these methods sends user to new pages such as main page, remove film page, add films and payment history page
	 * @param event ActionEvent
	 */
	@FXML
	private void gotoMainPage(ActionEvent event){
		if (DataHolder.getCurrentUser().getType().equals("client")) {
			DataHolder.setSelectedFilmTitle("");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("showfilms.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (DataHolder.getCurrentUser().getType().equals("admin")){
			DataHolder.setSelectedFilmTitle("");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void selectSeat(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("selectSeat.fxml"));
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
		DataHolder.setSelectedFilmTitle("");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("searchFilm.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void addFilm(ActionEvent event){
		DataHolder.setSelectedFilmTitle("");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("addFilm.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void paymentHistory(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("paymentHistory.fxml"));
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
