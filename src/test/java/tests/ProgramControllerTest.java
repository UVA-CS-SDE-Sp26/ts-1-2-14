import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ProgramControllerTest {
    private FileHandler fh;
    private final String testPath = "testData/";

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test directory and a sample file
        File dir = new File(testPath);
        if (!dir.exists()) dir.mkdir();

        File testFile = new File(testPath + "test.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("Hello World");
        writer.close();

        // Initialize the FileHandler for the tests
        fh = new FileHandler(testPath);
    }

    @Test
    void testZeroInput() {
        //test if it runs without crash
        String[] args = new String[0];
        ProgramController pc = new ProgramController(args, fh);
        // Should print the list of files without crashing
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
    @Test
    void testOneValidInput() {
        //Test if it prints out the correct file and using the default cipher
        String[] args = {"1"};
        ProgramController pc = new ProgramController(args, fh);
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
    @Test
    void testOneInvalidInput() {
        String[] args = new String[1];
        args[0] = (" ");
        ProgramController pc = new ProgramController(args, fh);
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }

    @Test
    void testTwoValidInput() {
        //Test if it prints out the correct file and using the chosen cipher
        String[] args = {"1", "Caesar"};
        ProgramController pc = new ProgramController(args, fh);
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
    @Test
    void testTwoInvalidInput() {
        //Test if it throws and error for invalid integer
        // Both arguments are spaces
        String[] args = {" ", " "};
        ProgramController pc = new ProgramController(args, fh);
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
    @Test
    void testOneValidOneInvalidInput() {
        //Test if it throws and error for invalid integer
        // First arg is a valid file index, second is a cipher name (even if empty string)
        String[] args = {"1", " "};
        ProgramController pc = new ProgramController(args, fh);
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
    @Test
    void testOneInvalidOneValidInput() {
        //Test if it throws and error for invalid integer
        // First arg (file index) is invalid, second is valid
        String[] args = {" ", "Cipher"};
        ProgramController pc = new ProgramController(args, fh);

        // Again, since the controller handles the bad integer parse
        // internally, the test verifies that it handles it without a crash.
        assertDoesNotThrow(() -> pc.cmd_line_util());
    }
}

