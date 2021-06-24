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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/***
 * The controller for user searching film page
 * implements the Initializable interface, so initialize methods gets executed while loaded.
 */
public class SearchFilmController implements Initializable {
	@FXML
	private ComboBox<String> searchCategoryCombo;
//	@FXML
//	private Button btnGoToMainMenu, btnGoBack, btnSearch, btnBuyTicket;
	@FXML
	private TextField txtSearch;
	private Stage stage;
	private Scene scene;
	private ArrayList<Film> films;
	private Film film;

	/**
	 * method that get executed while program is loaded
	 * and set some data for combo boxes such as film search types.
	 * @param url URL
	 * @param resourceBundle ResourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ObservableList<String> options = FXCollections.observableArrayList("Film Name", "Film Type", "Film Language",
				"Film Released Date");
		searchCategoryCombo.setItems(options);
	}

	/**
	 * this method is responsible for selecting searching film according to user selected
	 * search category and calls other methods to search for result and get films.
	 * @param event ActionEvent
	 */
	@FXML
	private void search(ActionEvent event){
		// showing warning if no search category was selected
		if(searchCategoryCombo.getSelectionModel().getSelectedItem() == null){
			Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select Search Category and Enter Search Word");
			alert.show();
		}else {
			try {
				// specifying search category
				String choice = searchCategoryCombo.getSelectionModel().getSelectedItem();
				switch (choice) {
					// searching film by name
					case "Film Name":
						film = Film.searchFilmsByName(txtSearch.getText().toLowerCase());
						showFilmSearchedByName(event);
						break;
					// searching film by type
					case "Film Type":
						films = Film.searchFilmsByType(txtSearch.getText().toLowerCase());
						ShowFilmsController.films = films;
						showSearchResult(event);
						break;
					// searching film by language
					case "Film Language":
						films = Film.searchFilmsByLanguage(txtSearch.getText().toLowerCase());
						ShowFilmsController.films = films;
						showSearchResult(event);
						break;
					// searching film by released date
					case "Film Released Date":
						films = Film.searchFilmsByReleasedDate(txtSearch.getText().toLowerCase());
						ShowFilmsController.films = films;
						showSearchResult(event);
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method shows film searched by user according to film type, film released date and film language.
	 * there could be more than one film shown as a result.
	 * @param event Action
	 */
	private void showSearchResult(ActionEvent event){
		// showing warning if no film founded
		if (ShowFilmsController.films.size() == 0){
			Alert alert = new Alert(Alert.AlertType.WARNING, "No film has ben found");
			alert.show();
		}

		// showing films
		else {
			DataHolder.setSelectedFilmTitle("");
			DataHolder.selectedSeats.clear();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("showfilms.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				ShowFilmsController.films = Film.readFilms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method shows the result of film user search by film name.
	 * @param event ActionEvent
	 */
	private void showFilmSearchedByName(ActionEvent event){
		// showing warning if no film was found according to user input
		if (film == null){
			Alert alert = new Alert(Alert.AlertType.WARNING, "No film has been found");
			alert.show();
		}
		// showing film that was searched
		else {
			try {
				DataHolder.setSelectedFilmTitle(film.getName());
				Parent root = FXMLLoader.load(getClass().getResource("showSelectedFilm.fxml"));
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * these methods sends user to new pages such as main page, booking history page and buy ticket page
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
	private void showBookingHistory(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("bookingHistory.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch (Exception e){
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
}
