package hihihaha.message;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskContainerTest {

    @Test
    public void processQuery_todo_addsTaskAndReturnsAddMessage() {
        TaskContainer tc = new TaskContainer();

        Message out = tc.processQuery(new UnitMessage("todo read book"));
        assertEquals(1, tc.size());

        Todo expectedTodo = new Todo("read book");
        Message expected = new Message(List.of(
                "Got it. I've added this task:",
                "  " + expectedTodo.toString(),
                "Now you have 1 tasks in the list:"));
        assertEquals(expected.toResponse(), out.toResponse());
    }

    @Test
    public void processQuery_listWithExtraParam_returnsInvalidFormatMessage() {
        TaskContainer tc = new TaskContainer();
        Message out = tc.processQuery(new UnitMessage("list extra"));
        assertEquals("Sorry, I cannot do that. The format is invalid >.<\n", out.toResponse());
    }

    @Test
    public void processQuery_markWithoutIndex_returnsInvalidIndexMessage() {
        TaskContainer tc = new TaskContainer();
        Message out = tc.processQuery(new UnitMessage("mark"));
        assertEquals("Sorry, I cannot do that. The index is invalid >.<\n", out.toResponse());
    }

    @Test
    public void processQuery_delete_removesTask() {
        TaskContainer tc = new TaskContainer();
        tc.processQuery(new UnitMessage("todo read book"));
        assertEquals(1, tc.size());

        tc.processQuery(new UnitMessage("delete 1"));
        assertEquals(0, tc.size());
    }

    @Test
    public void processQuery_findWithoutKeyword_returnsInvalidFormatMessage() {
        TaskContainer tc = new TaskContainer();
        Message out = tc.processQuery(new UnitMessage("find"));
        assertEquals("Sorry, I cannot do that. The format is invalid >.<\n", out.toResponse());
    }
}
