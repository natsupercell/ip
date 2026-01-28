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

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", this.taskType, this.checker(), super.toString());
    }
}
