import java.util.Scanner;
import java.util.List;
public class Hihihaha {
    private Scanner sc;
    private static final String chatBotName = "Hihihahaaaaa";
    private static final Message hiMessage = new Message(
            List.of(
                    "Hello! I'm " + chatBotName,
                    "What can I do for you?"
            )
    );
    private static final Message byeMessage = new Message("Bye. Hope to see you again soon!");
    private static final Message sayGoodBye = new Message("bye");

    public void Start() {
        sc = new Scanner(System.in);
        Message.Display(hiMessage);
    }

    public void Exit() {
        Message.Display(byeMessage);
        sc.close();
    }

    public void Run() {
        Start();
        Message message = Message.GetMessage(sc);
        while (!message.equals(sayGoodBye)) {
            Message.Display(message);
            message = Message.GetMessage(sc);
        }
        Exit();
    }

    public static void main(String[] args) {
        Hihihaha hihihaha = new Hihihaha();
        hihihaha.Run();
    }
}
