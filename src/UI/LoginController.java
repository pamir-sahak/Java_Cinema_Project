package UI;

import classes.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;

/***
 * the controller class for first window in this app (login + sign up page)
 */
public class LoginController {

	@FXML
	private Pane container;
	@FXML
	private Button btnSlider;
	@FXML
	TextField txtUserName;
	@FXML
	PasswordField txtPassword;
	@FXML
	TextField txtSignInUserName;
	@FXML
	TextField txtSignInEmail;
	@FXML
	TextField txtSignInTelephone;
	@FXML
	PasswordField txtSignInPassword;
	@FXML
	PasswordField txtSignInPassword2;
	@FXML
	Label lblAlert;

	private Stage stage;
	private Scene scene;

	/***
	 * the methods slides the front slider and give user to sign up or login
	 */
	@FXML
	private void slide() {
		lblAlert.setVisible(false);

		// if the button text is 'Sign up' it will can the slideToLeft() metot
		if (btnSlider.getText().toLowerCase().equals("Sign up".toLowerCase())) {
			slideToLeft();
			btnSlider.setText("Log in");
		} else {
			// calls slideToRight() method
			slideToRight();
			btnSlider.setText("Sign up");
		}
	}

	/***
	 * this methods slides the front slide bar to left so user can see sign up page
	 */
	@FXML
	private void slideToLeft() {
		TranslateTransition transition = new TranslateTransition(Duration.seconds(1), container);
		transition.setToX(-275);
		transition.play();
	}

	/***
	 * this methods slides the front slide bar to right so user can see login in page
	 */
	@FXML
	private void slideToRight() {

		TranslateTransition translate = new TranslateTransition(Duration.seconds(1.20), container);
		translate.setToX(0);
		translate.play();
	}

	/***
	 * these methods opens instagram, linkedin and twitter page in browser
	 * @throws Exception exception
	 */
	@FXML
	private void goToLinkedIn() throws Exception {
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://tr.linkedin.com/in/pamir-sahak"));
	}

	@FXML
	private void goToInstagram() throws Exception {
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://www.instagram.com/pamir_sahak/"));
	}

	@FXML
	private void goToTwitter() throws Exception {
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("https://twitter.com/pamir_sahak"));
	}

	/***
	 * this method checks the entered user name and password if both are correct opens new
	 * pages according go user type (admin / client)
	 * @param actionEvent ActionEvent
	 */
	@FXML
	private void login(javafx.event.ActionEvent actionEvent) {
		// reads all users in system
		ArrayList<Person> users = Person.readAllUsers();

		// checking for user name and password
		for (Person user : users) {
			if (txtUserName.getText().equals(user.getName())
					&& txtPassword.getText().equals(user.getAccount().getPassword())) {
				DataHolder.setCurrentUser(user);

				// client's main page is opened
				if (user.getType().equals("client")) {
					try {

						Parent root = FXMLLoader.load(getClass().getResource("showfilms.fxml"));
						stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.setResizable(false);
						stage.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// admin's main page is opened
				else if (user.getType().equals("admin")) {
					try {
						Parent root = FXMLLoader.load(getClass().getResource("adminPanel.fxml"));
						stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.setResizable(false);
						stage.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// gives warning if user name or password is incorrect.
			else {
				lblAlert.setVisible(true);
				lblAlert.setText("user name or password is incorrect");
			}
		}
	}

	/***
	 * this method adds a new user to file so user can later login
	 */
	@FXML
	private void signIn (){
			Client.addClient(txtSignInUserName.getText(),
					txtSignInEmail.getText(),
					txtSignInTelephone.getText(),
					txtSignInPassword.getText(),
					txtSignInPassword2.getText());
		}


}
