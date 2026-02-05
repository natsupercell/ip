package hihihaha.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class representing special messages, capable of storing essential data
 * related to task management. Provides core task manipulation logic.
 */
public abstract class Task extends UnitMessage {
    protected static final DateTimeFormatter READ_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter WRITE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    protected char taskType;
    protected boolean isDone;
    private static final String HARD_SPLITTER = " \\|\\| ";

    /**
     * Instantiates a Task.
     * 
     * @param task
     *            The task to be stored in the object.
     */
    Task(String task) {
        super(task);
        this.isDone = false;
    }

    /**
     * A helper function for the toString() method.
     * 
     * @return A symbol indicating whether the task is marked as done or not.
     */
    protected char checker() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task (as not done).
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Converts data into task.
     * 
     * @param string
     *            String representation of the task.
     * @return Task corresponding to the string.
     */
    public static Task dataToTask(String string) throws IllegalArgumentException {
        try {
            String[] params = string.split(HARD_SPLITTER);
            if (params.length < 3) {
                throw new IllegalArgumentException();
            }
            String taskTypeString = params[0];
            String isDoneString = params[1];
            String taskString = params[2];
            Task task;

            switch (taskTypeString) {
            case "T" :
                task = new Todo(taskString);
                break;
            case "D" :
                try {
                    task = new Deadline(taskString, LocalDateTime.parse(params[3], READ_FORMAT));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException();
                }
                break;
            case "E" :
                try {
                    task = new Event(taskString, LocalDateTime.parse(params[3], READ_FORMAT),
                            LocalDateTime.parse(params[4], READ_FORMAT));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException();
                }
                break;
            default :
                throw new IllegalArgumentException();
            }

            switch (isDoneString) {
            case "true" :
                task.mark();
                break;
            case "false" :
                break;
            default :
                throw new IllegalArgumentException();
            }

            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Converts task into data Implemented explicitly for each subclass of Task.
     * 
     * @return Data representation of task.
     */
    abstract String taskToData();

    /**
     * Converts task into data.
     * 
     * @param task
     *            Task to be converted into string.
     * @return Data representation of task.
     */
    public static String taskToData(Task task) {
        return task.taskToData();
    }

    /*
     * @Override public String toString() { return String.format("[%c][%c] %s",
     * this.taskType, this.checker(), super.toString()); }
     */

    /**
     * Checks if the task's string representation contains the specified keyword.
     * 
     * @param keyword
     *            The sequence of characters to search for.
     * @return {@code true} if the keyword is found within the task string;
     *         {@code false} otherwise.
     */
    public boolean nameContains(String keyword) {
        return this.toString().contains(keyword);
    }
}
