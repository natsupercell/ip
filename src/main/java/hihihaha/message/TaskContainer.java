package hihihaha.message;

import hihihaha.StringTrimmer;

import java.util.ArrayList;
import java.util.List;

// TODO: update core methods: mark, unmark, etc
// TODO: using the newly built and more robust displayCustom and message manipulation methods

/**
 * A class used to store and manage Tasks. Provides core task manipulation logic
 * (mark, unmark, add, delete,... Tasks).
 */
public class TaskContainer extends Message {
    protected List<Task> tasks;

    public TaskContainer() {
        tasks = new ArrayList<>();
    }

    public TaskContainer(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the container is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Message toMessage() {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            messages.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        return new Message(messages);
    }

    /**
     * Lists the tasks in the TaskContainer, with custom message before listing.
     * 
     * @param startMessage
     *            Message before listing.
     */
    public Message displayCustom(String startMessage) {
        Message output = this.toMessage();
        output.addFront(startMessage);
        return output;
    }

    /**
     * Lists the tasks in the TaskContainer, with custom message before and after
     * listing.
     * 
     * @param startMessage
     *            Message before listing.
     * @param endMessage
     *            Message after listing.
     */
    public Message displayCustom(String startMessage, String endMessage) {
        Message output = this.toMessage();
        output.addFront(startMessage);
        output.addBack(endMessage);
        return output;
    }

    /**
     * Returns error message.
     */
    private Message displayInvalidIndexErrorMessage() {
        Message invalidIndexErrorMessage = new Message("Sorry, I cannot do that. The index is invalid >.<");
        return invalidIndexErrorMessage;
    }

    /**
     * Returns error message.
     */
    private Message displayInvalidFormatErrorMessage() {
        Message invalidFormatErrorMessage = new Message("Sorry, I cannot do that. The format is invalid >.<");
        return invalidFormatErrorMessage;
    }

    /**
     * Returns error message.
     */
    private Message displayInvalidPromptErrorMessage() {
        Message invalidPromptErrorMessage = new Message("Sorry, I don't understand what you are saying >.<");
        return invalidPromptErrorMessage;
    }

    /**
     * Marks a task as done. Returns relevant messages.
     * 
     * @param i
     *            The position of the task to be marked.
     */
    public Message markTask(int i) {
        i--;
        String whatever = "Nice! I've marked this task as done:";
        Task t = tasks.get(i);
        t.mark();
        return new Message(List.of(whatever, "  " + t.toString()));
    }

    /**
     * Unmarks a task (as not done). Returns relevant messages.
     * 
     * @param i
     *            The position of the task to be unmarked.
     */
    public Message unmarkTask(int i) {
        i--;
        String whatever = "OK, I've marked this task as not done yet:";
        Task t = tasks.get(i);
        t.unmark();
        return new Message(List.of(whatever, "  " + t.toString()));
    }

    /**
     * Returns the string that lists the tasks in the TaskContainer.
     */
    public Message listTask() {
        String list = "Here are the tasks in your list:";
        return displayCustom(list);
    }

    /**
     * Deletes a task. Returns relevant messages.
     * 
     * @param i
     *            The position of the task to be deleted.
     */
    public Message deleteTask(int i) {
        i--;
        String removeTask = "Noted. I've removed this task:";
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        String sizeReport = String.format("Now you have %d tasks in the list.", this.tasks.size());
        return new Message(List.of(removeTask, "  " + task.toString(), sizeReport));
    }

    /**
     * Adds a task to the end of the list. Returns relevant messages.
     * 
     * @param task
     *            Task to be added.
     */
    public Message addTask(Task task) {
        String addTask = "Got it. I've added this task:";
        this.tasks.add(task);
        String sizeReport = String.format("Now you have %d tasks in the list.", this.tasks.size());
        return new Message(List.of(addTask, "  " + task.toString(), sizeReport));
    }

    /**
     * Adds task to the end of the list, without returning log messages.
     * 
     * @param task
     *            Task to be added.
     */
    public void loadTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Finds and displays tasks that contain the specified keyword in their description.
     * @param keyword The sequence of characters to search for within the task list.
     */
    public Message findTask(String keyword) {
        final String trimmedKeyword = StringTrimmer.trim(keyword); // allowing user to accidentally add more spaces at
                                                                   // the end of command

        String taskFound = "Here are the matching tasks in your list:";
        String noTaskFound = "There is no task with the keyword: " + trimmedKeyword;

        TaskContainer result = new TaskContainer(
                tasks.stream().filter(task -> task.nameContains(trimmedKeyword)).toList());

        Message messages = result.toMessage();
        if (!result.isEmpty()) {
            messages.addFront(taskFound);
        } else {
            messages.addFront(noTaskFound);
        }

        return messages;
    }

    /**
     * Processes messages from the user input, and then makes actions based on that.
     * 
     * @param message
     *            Message to be processed.
     */
    public Message processQuery(UnitMessage message) {
        String string = message.toString();
        String prompt;
        String param = null;
        int split = string.length(); // handle corner case where there's actually no whitespace

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                split = i;
                break;
            }
        }

        prompt = string.substring(0, split);
        if (split != string.length()) // handle corner case where there's actually no whitespace
            param = string.substring(split + 1);

        Message out = null;
        switch (prompt) {
        case "list":
            out = this.listTask();
            break;
        case "find":
            out = this.findTask(param);
            break;
        case "mark":
            try {
                Integer x = Integer.valueOf(param);
                out =  this.markTask(x);
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
                out = this.unmarkTask(x);
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
                out = this.deleteTask(x);
            } catch (NumberFormatException e) {
                displayInvalidIndexErrorMessage();
            } catch (IndexOutOfBoundsException e) {
                displayInvalidIndexErrorMessage();
            } finally {
                break;
            }
        case "todo":
            System.out.println(prompt);
            try {
                Todo task = Todo.produce(param);
                out = this.addTask(task);
            } catch (IllegalArgumentException e) {
                displayInvalidFormatErrorMessage();
            } finally {
                break;
            }
        case "deadline":
            try {
                Deadline task = Deadline.produce(param);
                out = this.addTask(task);
            } catch (IllegalArgumentException e) {
                displayInvalidFormatErrorMessage();
            } finally {
                break;
            }
        case "event":
            try {
                Event task = Event.produce(param);
                out = this.addTask(task);
            } catch (IllegalArgumentException e) {
                displayInvalidFormatErrorMessage();
            } finally {
                break;
            }
        default:
            out = displayInvalidPromptErrorMessage();
        }
        return out;
    }

    /**
     * Converts the whole container into a file (as string).
     * 
     * @return The string format of the data to be written on the data file.
     */
    @Override
    public String toString() {
        String out = "";
        boolean isBeginning = true;
        for (Task task : tasks) {
            if (isBeginning) { // help removing unnecessary ends of line
                isBeginning = false;
                out += task.taskToData();
                continue;
            }
            out += "\n" + task.taskToData();
        }
        return out;
    }
}
