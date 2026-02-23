package hihihaha.message;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void produce_validInput_success() {
        Deadline d = Deadline.produce("return book /by 20-11-2022");
        assertEquals(LocalDate.of(2022, 11, 20), d.getBy());
        assertTrue(d.toString().contains("return book"));
    }

    @Test
    public void produce_missingBy_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Deadline.produce("return book"));
    }

    @Test
    public void produce_wrongDateFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Deadline.produce("return book /by 2022-11-20"));
    }

    @Test
    public void produce_blankDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Deadline.produce("   /by 20-11-2022"));
    }
}
