package UI;

import classes.DataHolder;
import classes.Film;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

/***
 * The controller class for each film show in main page
 */
public class filmController {
	@FXML
	ImageView img;
	@FXML
	Label lblID;
	static Stage stage;
	static Scene scene;
	private InputStream file;

	private Film film;

	/***
	 * gets every film and sets its image in main page
	 * @param film Film
	 */
	void setData(Film film){
		try {
			this.film = film;
			// gets film's image path if path is relative
			file = getClass().getResourceAsStream(film.getImgSrc());

			// gets film's image path if path is Absolute
			if (file == null) {
				file = new FileInputStream(film.getImgSrc());
			}

			// setting image
			Image image = new Image(file);
//			Image image = new Image((InputStream) file);
			img.setImage(image);

			// just a hiding film name for latter usage (:
			lblID.setText(film.getName());
			lblID.setVisible(false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/***
	 * this method sends user to selected film page.
	 * @param event ActionEvent
	 */
	public void openFilm(javafx.event.Event event){
		// setting selected film name
		DataHolder.setSelectedFilmTitle(lblID.getText());

		try {
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
}
