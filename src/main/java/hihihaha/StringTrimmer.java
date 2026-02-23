package hihihaha;

/**
 * Utility class, used to trim off excess whitespace of a string.
 */
public class StringTrimmer {
    /**
     * Trims leading and trailing spaces from the given string.
     * <p>
     * Note: This method only treats the space character ({@code ' '}) as
     * whitespace (to keep behavior simple and predictable for iP).
     * </p>
     *
     * @param s Input string.
     * @return The trimmed string.
     */
    public static String trim(String s) {
        int l = 0; // begin of new string
        int r = 0; // end of new string

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                l = i;
                break;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                r = i + 1;
                break;
            }
        }

        return s.substring(l, r);
    }
}
