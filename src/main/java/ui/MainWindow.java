package ui;

import hihihaha.Hihihaha;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 * Built with the help of ChatGPT.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hihihaha hihihaha;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the hihihaha.Hihihaha instance */
    public void setChatBot(Hihihaha hihihaha) {
        this.hihihaha = hihihaha;

        // Show the startup message in the GUI
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(hihihaha.getWelcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ui.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hihihaha.getResponse(input);

        boolean isBye = input != null && input.trim().equalsIgnoreCase("bye");

        // Keep red error bubbles ONLY when it’s an actual error.
        // (Exclude "bye" so it never becomes red even if your bye message format changes.)
        boolean isError = !isBye
                && response != null
                && response.startsWith("Sorry");  // adjust prefix if yours differs

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        if (isError) {
            dialogContainer.getChildren().add(DialogBox.getErrorDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }

        userInput.clear();

        // Exit after showing the goodbye message
        if (isBye) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            Platform.runLater(Platform::exit);
        }
    }
}
