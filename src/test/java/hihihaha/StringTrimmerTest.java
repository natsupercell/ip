package hihihaha;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTrimmerTest {
    @Test
    public void trim_hollowString_emptyStringReturned() {
        assertEquals(StringTrimmer.trim("      "), "");
    }

    @Test
    public void trim_stringWithGapInBetween_gapPersists() {
        assertEquals(StringTrimmer.trim(" a ba"), "a ba");
    }
}
