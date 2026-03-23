package hihihaha.message;

import hihihaha.StringTrimmer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
     * @return Number of tasks currently stored.
     */
    public int size() {
        return tasks.size();
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
        return new Message("Sorry, I cannot do that. The index is invalid >.<");
    }

    /**
     * Returns error message.
     */
    private Message displayInvalidFormatErrorMessage() {
        return new Message("Sorry, I cannot do that. The format is invalid >.<");
    }

    /**
     * Returns error message.
     */
    private Message displayInvalidPromptErrorMessage() {
        return new Message("Sorry, I don't understand what you are saying >.<");
    }

    /**
     * Marks a task as done. Returns relevant messages.
     * 
     * @param i
     *            The position of the task to be marked.
     */
    public Message markTask(int i) {
        i--;
        Task task = tasks.get(i);
        task.mark();

        String whatever = "Nice! I've marked this task as done:";
        return new Message(List.of(whatever, "  " + task.toString()));
    }

    /**
     * Unmarks a task (as not done). Returns relevant messages.
     * 
     * @param i
     *            The position of the task to be unmarked.
     */
    public Message unmarkTask(int i) {
        i--;
        Task task = tasks.get(i);
        task.unmark();

        String whatever = "OK, I've marked this task as not done yet:";
        return new Message(List.of(whatever, "  " + task.toString()));
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
        Task task = this.tasks.get(i);
        this.tasks.remove(i);

        String removeTask = "Noted. I've removed this task:";
        String sizeReport = String.format("Now you have %d tasks in the list:", this.tasks.size());
        return new Message(List.of(removeTask, "  " + task.toString(), sizeReport));
    }

    /**
     * Adds a task to the end of the list. Returns relevant messages.
     * 
     * @param task
     *            Task to be added.
     */
    public Message addTask(Task task) {
        this.tasks.add(task);

        String addTask = "Got it. I've added this task:";
        String sizeReport = String.format("Now you have %d tasks in the list:", this.tasks.size());
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
     * Finds and displays tasks that contain the specified keyword in their
     * description.
     * 
     * @param keyword
     *            The sequence of characters to search for within the task list.
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
     * Reminds user of upcoming deadlines.
     */
    public Message remind() {
        LocalDate now = LocalDate.now();

        TaskContainer result = new TaskContainer(
                tasks.stream().filter(task -> task instanceof Deadline).map(task -> (Deadline) task)
                        .filter(deadline -> deadline.getBy().isAfter(now)).map(task -> (Task) task).toList());

        String remind = String.format("You have %d upcoming deadlines:", result.size());
        Message messages = result.toMessage();
        messages.addFront(remind);

        return messages;
    }

    private Message processListQuery(String param) {
        if (!param.isBlank()) {
            return displayInvalidFormatErrorMessage();
        }

        return listTask();
    }

    private Message processFindQuery(String param) {
        if (param.isBlank()) {
            return displayInvalidFormatErrorMessage();
        }

        return findTask(param);
    }

    private Message processRemindQuery(String param) {
        if (!param.isBlank()) {
            return displayInvalidFormatErrorMessage();
        }

        return remind();
    }

    private Message processMarkQuery(String param) {
        try {
            if (param.isBlank()) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(param);
            return this.markTask(x);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return displayInvalidIndexErrorMessage();
        }
    }

    private Message processUnmarkQuery(String param) {
        try {
            if (param.isBlank()) {
                throw new NumberFormatException();
            }
            int x = Integer.valueOf(param);
            return this.unmarkTask(x);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return displayInvalidIndexErrorMessage();
        }
    }

    private Message processDeleteQuery(String param) {
        try {
            if (param.isBlank()) {
                throw new NumberFormatException();
            }
            int x = Integer.parseInt(param);
            return this.deleteTask(x);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return displayInvalidIndexErrorMessage();
        }
    }

    private Message processTodoQuery(String param) {
        try {
            Todo task = Todo.produce(param);
            return this.addTask(task);
        } catch (IllegalArgumentException e) {
            return displayInvalidFormatErrorMessage();
        }
    }

    private Message processDeadlineQuery(String param) {
        try {
            Deadline task = Deadline.produce(param);
            return this.addTask(task);
        } catch (IllegalArgumentException e) {
            return displayInvalidFormatErrorMessage();
        }
    }

    private Message processEventQuery(String param) {
        try {
            Event task = Event.produce(param);
            return this.addTask(task);
        } catch (IllegalArgumentException e) {
            return displayInvalidFormatErrorMessage();
        }
    }

    private List<String> getArgs(UnitMessage message) {
        String string = message.toString();
        String prompt;
        String param = "";
        int split = string.length(); // handle corner case where there's actually no whitespace

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                split = i;
                break;
            }
        }

        prompt = string.substring(0, split);
        if (split != string.length()) { // handle corner case where there's actually no whitespace
            param = string.substring(split + 1).trim();
        }

        return List.<String>of(prompt, param);
    }

    /**
     * Processes messages from the user input, and then makes actions based on that.
     * 
     * @param message
     *            Message to be processed.
     */
    public Message processQuery(UnitMessage message) {
        List<String> args = getArgs(message);
        String prompt = args.get(0);
        String param = args.get(1);

        switch (prompt) {
        case "list" :
            return processListQuery(param);
        case "find" :
            return processFindQuery(param);
        case "remind" :
            return processRemindQuery(param);
        case "mark" :
            return processMarkQuery(param);
        case "unmark" :
            return processUnmarkQuery(param);
        case "delete" :
            return processDeleteQuery(param);
        case "todo" :
            return processTodoQuery(param);
        case "deadline" :
            return processDeadlineQuery(param);
        case "event" :
            return processEventQuery(param);
        default :
            return displayInvalidPromptErrorMessage();
        }
    }

    /**
     * Converts the whole container into a file (as string).
     * 
     * @return The string format of the data to be written on the data file.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        boolean isBeginning = true;

        for (Task task : tasks) {
            if (isBeginning) { // help removing unnecessary ends of line
                isBeginning = false;
                out.append(task.taskToData());
                continue;
            }
            out.append("\n").append(task.taskToData());
        }

        return out.toString();
    }
}
