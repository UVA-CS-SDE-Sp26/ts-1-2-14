import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


class CipherSimpleTest {

    @Test
    void testDecipherMethod() {
        CipherSimple cs = new CipherSimple();
        String actualAlphabet = "ABC";
        String cipherAlphabet = "123";

        Assertions.assertEquals("ABC", cs.decipher("123", actualAlphabet, cipherAlphabet),
                "The decipher method should correctly map characters based on the two input lines.");
    }
    @Test
    void testDecipherWithUnknownChars() {
        CipherSimple cs = new CipherSimple();
        Assertions.assertEquals("A1B", cs.decipher("A2B", "1", "2"),
                "Characters not found in the cipher line should remain untouched.");
    }

    @Test
    void testHasDuplicatesValidation() {
        CipherSimple cs = new CipherSimple();
        Assertions.assertTrue(cs.hasDuplicates("AABC"), "Validation should detect duplicate characters in a key.");
        Assertions.assertFalse(cs.hasDuplicates("ABC"), "Validation should pass for unique characters.");
    }

    @Test
    void testLoadKeyLinesReturnsNullOnMissingFile() {
        CipherSimple cs = new CipherSimple();
        Assertions.assertNull(cs.loadKeyLines("invalid/path/key.txt"),
                "Method should handle missing files by returning null instead of crashing.");
    }
}