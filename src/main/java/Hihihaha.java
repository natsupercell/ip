import java.util.Scanner;
import java.util.List;
public class Hihihaha {
    private Scanner sc;
    private TaskContainer tc;
    private static final String chatBotName = "Hihihahaaaaa";
    private static final Message hiMessage = new Message(
            List.of(
                    "Hello! I'm " + chatBotName,
                    "What can I do for you?"
            )
    );
    private static final Message byeMessage = new Message("Bye. Hope to see you again soon!");
    private static final Message sayGoodBye = new UnitMessage("bye");

    public void start() {
        sc = new Scanner(System.in);
        tc = new TaskContainer();
        Message.display(hiMessage);
    }

    public void exit() {
        Message.display(byeMessage);
        sc.close();
    }

    public void run() {
        start();
        UnitMessage message = Message.getMessage(sc);
        while (!message.equals(sayGoodBye)) {
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
