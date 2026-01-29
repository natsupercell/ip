import java.util.ArrayList;
import java.util.List;

/**
 * A class used to store and manage Tasks
 */
public class TaskContainer extends Message {
    protected List<Task> tasks;

    /**
     * Default constructor
     */
    TaskContainer() {
        tasks = new ArrayList<Task>();
    }

    @Override
    public void display() {
        String whatever = "Here are the tasks in your list:";
        System.out.println(LINING);
        System.out.println(whatever);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println(LINING);
    }

    /**
     * Displays error message
     */
    private void displayInvalidIndexErrorMessage() {
        Message invalidIndexErrorMessage =
                new Message("Sorry, I cannot do that. The index is invalid >.<");
        display(invalidIndexErrorMessage);
    }

    /**
     * Displays error message
     */
    private void displayInvalidFormatErrorMessage() {
        Message invalidFormatErrorMessage =
                new Message("Sorry, I cannot do that. The format is invalid >.<");
        display(invalidFormatErrorMessage);
    }

    /**
     * Displays error message
     */
    private void displayInvalidPromptErrorMessage() {
        Message invalidPromptErrorMessage =
                new Message("Sorry, I don't understand what you are saying >.<");
        display(invalidPromptErrorMessage);
    }

    /**
     * Marks a task as done
     * Print relevant messages
     * @param i The position of the task to be marked
     */
    public void mark(int i) {
        i--;
        String whatever = "Nice! I've marked this task as done:";
        Task t = tasks.get(i);
        t.mark();
        display(new Message(List.of(whatever, "  " + t.toString())));
    }

    /**
     * Unmarks a task (as not done)
     * Print relevant messages
     * @param i The position of the task to be unmarked
     */
    public void unmark(int i) {
        i--;
        String whatever = "OK, I've marked this task as not done yet:";
        Task t = tasks.get(i);
        t.unmark();
        display(new Message(List.of(whatever, "  " + t.toString())));
    }

    /**
     * Deletes a task
     * Print relevant messages
     * @param i The position of the task to be deleted
     */
    public void delete(int i) {
        i--;
        String removeTask = "Noted. I've removed this task:";
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        String sizeReport = String.format("Now you have %d tasks in the list.", this.tasks.size());
        display(new Message(List.of(
                removeTask,
                "  " + task.toString(),
                sizeReport
        )));
    }

    /**
     * Adds a task to the end of the list
     * Print relevant messages
     * @param task Task to be added
     */
    public void addTask(Task task) {
        String addTask = "Got it. I've added this task:";
        this.tasks.add(task);
        String sizeReport = String.format("Now you have %d tasks in the list.", this.tasks.size());
        display(new Message(List.of(
                addTask,
                "  " + task.toString(),
                sizeReport
        )));
    }

    /**
     * Simply add task to the end of the list, without printing log messages
     * @param task Task to be added
     */
    public void loadTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Processes messages from the user input, and makes actions based on that
     * @param message Message to be processed
     */
    public void processQuery(UnitMessage message) {
        String string = message.toString();
        String prompt;
        String param = null;
        int split = string.length(); // handle corner case where there's actually no whitespace

        for (int i = 0; i < string.length(); i++)
            if (string.charAt(i) == ' ') {
                split = i;
                break;
            }

        prompt = string.substring(0, split);
        if (split != string.length()) // handle corner case where there's actually no whitespace
            param = string.substring(split + 1);

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
            } catch (IndexOutOfBoundsException e) {
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
            } catch (IndexOutOfBoundsException e) {
                displayInvalidIndexErrorMessage();
            } finally {
                break;
            }
        case "delete":
            try {
                Integer x = Integer.valueOf(param);
                delete(x);
            } catch (NumberFormatException e) {
                displayInvalidIndexErrorMessage();
            } catch (IndexOutOfBoundsException e) {
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

    /**
     * Convert the whole container into a file (as string)
     * @return The string format of the data to be written on the data file
     */
    @Override
    public String toString() {
        String out = "";
        boolean isBeginning = true;
        for (Task task: tasks) {
            if (isBeginning) { // help removing unnecessary ends of line
                isBeginning = false;
                out += task;
                continue;
            }
            out += "\n" + task;
        }
        return out;
    }
}
