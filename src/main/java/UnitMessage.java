public class UnitMessage extends Message {
    UnitMessage(String message) {
        super(message);
    }

    public String get() {
        return message.get(0);
    }

    public void displayAddMessage() {
        String addMessage = "added: ";
        Message.display(addMessage + this.get());
    }
}
