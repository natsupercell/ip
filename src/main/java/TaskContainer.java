import java.util.ArrayList;
import java.util.List;

public class TaskContainer extends Message {
    protected List<Task> task;

    TaskContainer() {
        task = new ArrayList<Task>();
    }

    @Override
    public void display() {
        String whatever = "Here are the tasks in your list:";
        System.out.println(lining);
        System.out.println(whatever);
        for (int i = 0; i < task.size(); i++) {
            System.out.printf("%d.%s\n", i+1, task.get(i).toString());
        }
        System.out.println(lining);
    }

    private void displayInvalidIndexErrorMessage() {
        Message invalidIndexErrorMessage =
                new Message("Sorry, I cannot do that. The index is invalid >.<");
        display(invalidIndexErrorMessage);
    }

    private void displayInvalidFormatErrorMessage() {
        Message invalidFormatErrorMessage =
                new Message("Sorry, I cannot do that. The format is invalid >.<");
        display(invalidFormatErrorMessage);
    }

    private void displayInvalidPromptErrorMessage() {
        Message invalidPromptErrorMessage =
                new Message("Sorry, I don't understand what you are saying >.<");
        display(invalidPromptErrorMessage);
    }

    public void mark(int i) {
        i--;
        String whatever = "Nice! I've marked this task as done:";
        Task t = task.get(i);
        t.mark();
        display(new Message(List.of(whatever, "  " + t.toString())));
    }

    public void unmark(int i) {
        i--;
        String whatever = "OK, I've marked this task as not done yet:";
        Task t = task.get(i);
        t.unmark();
        display(new Message(List.of(whatever, "  " + t.toString())));
    }

    public void addTask(Task task) {
        String addTask = "Got it. I've added this task:";
        this.task.add(task);
        String sizeReport = String.format("Now you have %d tasks in the list.", this.task.size());
        display(new Message(List.of(
                addTask,
                "  " + task.toString(),
                sizeReport
        )));
    }

    public void processQuery(UnitMessage message) {
        String string = message.toString();
        String prompt;
        String param = null;
        int split = string.length(); // handle corner case where there's actually no whitespace

        for (int i = 0; i < string.length(); i++) if (string.charAt(i) == ' ') {
            split = i;
            break;
        }

        prompt = string.substring(0, split);
        if (split != string.length()) // handle corner case where there's actually no whitespace
            param = string.substring(split+1);

        switch (prompt) {
            case "list":
                this.display();
                break;
            case "mark":
                try {
                    Integer x = Integer.valueOf(param);
                    mark(x);
                } catch (NumberFormatException e) {
                    displayInvalidIndexErrorMessage();
                } catch (IndexOutOfBoundsException e){
                    displayInvalidIndexErrorMessage();
                } finally {
                    break;
                }
            case "unmark":
                try {
                    Integer x = Integer.valueOf(param);
                    unmark(x);
                } catch (NumberFormatException e) {
                    displayInvalidIndexErrorMessage();
                } catch (IndexOutOfBoundsException e){
                    displayInvalidIndexErrorMessage();
                } finally {
                    break;
                }
            case "todo":
                try {
                    ToDo task = ToDo.produce(param);
                    addTask(task);
                } catch (IllegalArgumentException e) {
                    displayInvalidFormatErrorMessage();
                } finally {
                    break;
                }
            case "deadline":
                try {
                    Deadline task = Deadline.produce(param);
                    addTask(task);
                } catch (IllegalArgumentException e) {
                    displayInvalidFormatErrorMessage();
                } finally {
                    break;
                }
            case "event":
                try {
                    Event task = Event.produce(param);
                    addTask(task);
                } catch (IllegalArgumentException e) {
                    displayInvalidFormatErrorMessage();
                } finally {
                    break;
                }
            default:
                displayInvalidPromptErrorMessage();
        }
    }
}
