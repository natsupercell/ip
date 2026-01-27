import java.util.List;
public class Hihihaha {
    private static final String chatBotName = "Hihihahaaaaa";
    private static final Message hiMessage = new Message(
            List.of(
                    "Hello! I'm " + chatBotName,
                    "What can I do for you?"
            )
    );
    private static final Message byeMessage = new Message("Bye. Hope to see you again soon!");

    public static void main(String[] args) {
        Message.Display(hiMessage);
        Message.Display(byeMessage);
    }
}
