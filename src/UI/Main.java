package UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    /**
    * this method isLunching the first window (login page)
    * @throws Exception exception
    * @param primaryStage Javafx.stage.Stage
    */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cinema");
        primaryStage.getIcons().add(new Image(".\\icons\\video.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
