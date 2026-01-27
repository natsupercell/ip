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
            System.out.printf("%d.%s\n", i+1, task.get(i).get());
        }
        System.out.println(lining);
    }

    private void displayInvalidIndexErrorMessage() {
        Message invalidIndexErrorMessage =
                new Message("Sorry, I cannot do that. The index is invalid!");
        display(invalidIndexErrorMessage);
    }

    public void mark(int i) {
        i--;
        String whatever = "Nice! I've marked this task as done:";
        if (i < 0 || i >= task.size()) {
            displayInvalidIndexErrorMessage();
            return;
        }
        Task t = task.get(i);
        t.mark();
        display(new Message(List.of(whatever, "  " + t.get())));
    }

    public void unmark(int i) {
        i--;
        String whatever = "OK, I've marked this task as not done yet:";
        if (i < 0 || i >= task.size()) {
            displayInvalidIndexErrorMessage();
            return;
        }
        Task t = task.get(i);
        t.unmark();
        display(new Message(List.of(whatever, "  " + t.get())));
    }

    public void addTask(UnitMessage message) {
        this.task.add(new Task(message.get()));
        message.displayAddMessage();
    }

    public void addTask(Task task) {
        this.task.add(task);
    }

    public void processQuery(UnitMessage message) {
        Message list = new UnitMessage("list");
        if (message.equals(list)) {
            display();
            return;
        }
        String string = message.get();
        String[] words = string.split(" ");
        if (words.length != 2) {
            addTask(message);
            return;
        }
        if (words[0].equals("mark") || words[0].equals("unmark")) {
            try {
                Integer x = Integer.valueOf(words[1]);
                if (words[0].equals("mark")) mark(x);
                else unmark(x);
            } catch (NumberFormatException e) {
                addTask(message);
                return;
            }
        } else {
            addTask(message);
            return;
        }
    }
}
