package ui;

import hihihaha.Hihihaha;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Ui.Duke using FXML.
 */
public class Main extends Application {

    private final Hihihaha hihihaha = new Hihihaha();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Load custom styles (A-BetterGui)
            scene.getStylesheets().add(Main.class.getResource("/view/styles.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Hihihaha");
            stage.setMinWidth(360);
            stage.setMinHeight(520);
            fxmlLoader.<MainWindow>getController().setChatBot(hihihaha); // inject the Ui.Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        // Ensures data is saved when user closes the window using the close button.
        hihihaha.shutdown();
    }
}
