package hihihaha.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void produce_validInput_success() {
        Event e = Event.produce("concert /from 20-11-2022 /to 21-11-2022");
        assertTrue(e.toString().contains("concert"));
        assertTrue(e.toString().contains("from:"));
        assertTrue(e.toString().contains("to:"));
    }

    @Test
    public void produce_wrongAttributeOrder_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Event.produce("concert /to 21-11-2022 /from 20-11-2022"));
    }

    @Test
    public void produce_fromAfterTo_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Event.produce("concert /from 22-11-2022 /to 21-11-2022"));
    }

    @Test
    public void produce_wrongDateFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Event.produce("concert /from 2022-11-20 /to 2022-11-21"));
    }
}
