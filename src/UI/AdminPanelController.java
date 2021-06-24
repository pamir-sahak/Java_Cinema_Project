package UI;

import classes.DataHolder;
import classes.Film;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * The controller for admin's main page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class AdminPanelController implements Initializable {

	@FXML
	private GridPane grid;

	private Stage stage;
	private Scene scene;

	/***
	 * method that get executed while program is loaded and show all films in main page.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// reading all films and adding them to grid pane
		ArrayList<Film> films = Film.readFilms();
		grid.getChildren().removeAll();
		int column = 0;
		int row = 1;
		try {
			for (int i = 0; i < films.size(); i++) {
				// loading every film
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("film.fxml"));
				AnchorPane anchorPane = fxmlLoader.load();

				// setting film information
				filmController filmController = fxmlLoader.getController();
				filmController.setData(films.get(i));

				// if current row's columns are full films are added to next row
				if (column == 3){
					column = 0;
					row++;
				}

				// adding films to grip pane
				if (films.get(i) != null) {
					grid.add(anchorPane, column++, row);
				}

				// adding gap between films
				GridPane.setMargin(anchorPane, new Insets(26.9));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * this method makes user log out from app and sends him to login page
	 * @param event ActionEvent
	 * @throws Exception Exception
	 */
	@FXML
	private void logout(ActionEvent event) throws Exception{
		DataHolder.setCurrentUser(null);
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	/***
	 * these methods sends user to new pages such as main page, remove film page and payment history page
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
