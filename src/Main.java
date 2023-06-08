import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    private static final String URLDB="jdbc:sqlite:GestionaleMedicinaDB";
    private Connection connection=null;

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
        try{
            if(connection==null||connection.isClosed()){
                connection= DriverManager.getConnection(URLDB);
                System.out.println("done");
            }
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("connection failed");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
