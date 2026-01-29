import java.util.Scanner;
import java.util.List;

/**
 * Our chatbot
 */
public class Hihihaha {
    private Scanner sc;
    private TaskContainer tc;
    private static final String THE_GOAT = "Hihihahaaaaa";
    private static final Message HI_MESSAGE = new Message(
            List.of(
                    "Hello! I'm " + THE_GOAT,
                    "What can I do for you?"
            )
    );
    private static final Message BYE_MESSAGE = new Message("Bye. Hope to see you again soon!");
    private static final Message BYE = new UnitMessage("bye");

    /**
     * Setups the chatbot
     */
    public void start() {
        DataManager.initializeFile();
        sc = new Scanner(System.in);
        tc = DataManager.read();
        Message.display(HI_MESSAGE);
    }

    /**
     * Terminates the chatbot
     */
    public void exit() {
        DataManager.save(tc);
        Message.display(BYE_MESSAGE);
        sc.close();
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        start();
        UnitMessage message = Message.getMessage(sc);
        while (!message.equals(BYE)) {
            tc.processQuery(message);
            message = Message.getMessage(sc);
        }
        exit();
    }

    public static void main(String[] args) {
        Hihihaha hihihaha = new Hihihaha();
        hihihaha.run();
    }
}
