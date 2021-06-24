package UI;

import classes.DataHolder;
import classes.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/***
 * The controller for admin Deleting films page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class DeleteFilmController implements Initializable {

	@FXML
	private ComboBox<String> filmsCombo;

	private Scene scene;
	private Stage stage;

	/**
	 * method that get executed while program is loaded.
	 * adds all film names to the combo box
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// adding all film name in combo box for selection
		ObservableList<String> films = FXCollections.observableArrayList();

		for (Film film : Film.readFilms()){
			films.add(film.getName());
		}
		filmsCombo.setItems(films);
	}

	/***
	 * these methods sends user to new pages such as main page, remove film page and payment history page
	 * @param event ActionEvent
	 */
	@FXML
	private void addFilm(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AddFilm.fxml"));
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
	private void deleteFilm(ActionEvent event){
		try {
			DataHolder.setSelectedFilmTitle(filmsCombo.getSelectionModel().getSelectedItem());
			Parent root = FXMLLoader.load(getClass().getResource("showSelectedFilm.fxml"));
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
