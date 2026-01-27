public class Task extends UnitMessage {
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
    public String get() {
        return String.format("[%c] %s", this.checker(), super.get());
    }
}
