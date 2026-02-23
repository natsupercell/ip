package hihihaha.message;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDataConversionTest {

    @Test
    public void taskToData_thenDataToTask_todoRoundTrip() {
        Todo t = new Todo("read book");
        t.mark();

        Task parsed = Task.dataToTask(t.taskToData());
        assertInstanceOf(Todo.class, parsed);
        assertEquals(t.taskToData(), parsed.taskToData());
    }

    @Test
    public void taskToData_thenDataToTask_deadlineRoundTrip() {
        Deadline d = new Deadline("return book", LocalDate.of(2022, 11, 20));

        Task parsed = Task.dataToTask(d.taskToData());
        assertInstanceOf(Deadline.class, parsed);
        assertEquals(d.taskToData(), parsed.taskToData());
    }

    @Test
    public void taskToData_thenDataToTask_eventRoundTrip() {
        Event e = new Event("concert", LocalDate.of(2022, 11, 20), LocalDate.of(2022, 11, 21));
        e.mark();

        Task parsed = Task.dataToTask(e.taskToData());
        assertInstanceOf(Event.class, parsed);
        assertEquals(e.taskToData(), parsed.taskToData());
    }

    @Test
    public void dataToTask_invalidData_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Task.dataToTask("X || true || something"));
    }
}
