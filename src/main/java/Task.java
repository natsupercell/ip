/**
 * Special messages, modified as Tasks
 */
public abstract class Task extends UnitMessage {
    protected char taskType;
    private boolean isDone;

    /**
     * Instantiates a Task
     * @param task The task to be stored in the object
     */
    Task(String task) {
        super(task);
        this.isDone = false;
    }

    /**
     * A helper function for the toString() method
     * @return A symbol indicating whether the task is marked as done or not
     */
    private char checker() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task (as not done)
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Converts string of the form "[?][?] ..." into task
     * @param string String representation of the task
     * @return Task corresponding to the string
     */
    public static Task stringToTask(String string) throws IllegalArgumentException {
        try {
            char taskTypeChar = string.charAt(1);
            char isDoneChar = string.charAt(4);
            String taskString = string.substring(7);
            Task task;

            switch (taskTypeChar) {
            case 'T':
                task = new ToDo(taskString);
                break;
            case 'D':
                task = new Deadline(taskString);
                break;
            case 'E':
                task = new Event(taskString);
                break;
            default:
                throw new IllegalArgumentException();
            }

            switch (isDoneChar) {
            case 'X':
                task.mark();
                break;
            case ' ':
                break;
            default:
                throw new IllegalArgumentException();
            }

            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", this.taskType, this.checker(), super.toString());
    }
}
