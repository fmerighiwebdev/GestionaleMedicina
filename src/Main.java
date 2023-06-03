import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Inizializza lo stage principale dell'applicazione
            Parent root = FXMLLoader.load(getClass().getResource("View/SceltaLogin.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestionale Medicina");
            primaryStage.setResizable(false);
            primaryStage.show();
            primaryStage.centerOnScreen();

            Image icon = new Image("View/images/medicine_logo.png");
            primaryStage.getIcons().add(icon);
        } catch (Exception eStart) {
            eStart.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
