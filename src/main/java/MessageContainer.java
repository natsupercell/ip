public class MessageContainer extends Message {
    public void AddMessage(String message) {
        this.message.add(message);
    }

    public void AddMessage(UnitMessage message) {
        this.message.add(message.Get());
    }

    public void DisplayAddMessage(UnitMessage message) {
        message.DisplayAddMessage();
    }

    @Override
    public void Display() {
        System.out.println(lining);
        for (int i = 0; i < message.size(); i++) {
            System.out.printf("%d. %s\n", i+1, message.get(i));
        }
        System.out.println(lining);
    }

    public static void Display(MessageContainer mc) {
        mc.Display();
    }
}
