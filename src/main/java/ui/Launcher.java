package ui;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        javafx.application.Application.launch(Main.class, args);
    }
}
