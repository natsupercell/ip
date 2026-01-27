public abstract class Task extends UnitMessage {
    protected char taskType;
    private boolean isDone;

    Task(String task) {
        super(task);
        this.isDone = false;
    }

    protected char checker() {
        return isDone ? 'X' : ' ';
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", this.taskType, this.checker(), super.toString());
    }
}
