public class UnitMessage extends Message {
    UnitMessage(String message) {
        super(message);
    }

    public String Get() {
        return message.get(0);
    }

    public void DisplayAddMessage() {
        String addMessage = "added: ";
        Message.Display(addMessage + this.Get());
    }
}
