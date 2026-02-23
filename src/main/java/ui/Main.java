package ui;

import hihihaha.Hihihaha;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Hihihaha using FXML.
 * Built with the help of ChatGPT.
 */
public class Main extends Application {

    private Hihihaha hihihaha = new Hihihaha();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(
                    Main.class.getResource("/view/styles.css").toExternalForm()
            );

            stage.setScene(scene);

            // Allow resizing but prevent too small window
            stage.setMinWidth(360);
            stage.setMinHeight(520);

            fxmlLoader.<MainWindow>getController().setChatBot(hihihaha);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (hihihaha != null) {
            hihihaha.shutdown();
        }
    }
}
