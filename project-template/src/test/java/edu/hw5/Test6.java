package edu.hw5;
;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test6 {

    @Test
    public void testSubstringTrue() {
        assertTrue(Task6.findSubstring("abc", "achfdbaabgabcaabg"));
        assertTrue(Task6.findSubstring("ab", "achfdbaabgabcaabg"));
        assertTrue(Task6.findSubstring("ac", "achfdbaabgabcaabg"));
        assertTrue(Task6.findSubstring("a", "achfdbaabgabcaabg"));
    }

    @Test
    public void testSubstringFalse() {
        assertFalse(Task6.findSubstring("abcd", "belachao"));
        assertFalse(Task6.findSubstring("xyz", "xyisgonnasavetheworld"));
        assertFalse(Task6.findSubstring("cba", "babababa"));
        assertFalse(Task6.findSubstring("abc", "acccc"));
    }
}
