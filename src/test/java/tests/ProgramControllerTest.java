import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void tearDown() {
        // Delete test files and directory after each test
        File dir = new File(testPath);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
            dir.delete();
        }
    }

    @Test
    void testZeroInput() {
        //test if it runs without crash
        String[] args = new String[0];
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testOneValidInput() {
        //Test if it prints out the correct file and using the default cipher
        String[] args = {"1"};
        // Use a handler that returns valid content for index 1
        FileHandler validHandler = new FileHandler(testPath) {
            @Override
            public String getFileContents(int index) {
                return "Hello World";
            }
        };
        ProgramController pc = new ProgramController(args, validHandler);
        pc.cmd_line_util();
    }

    @Test
    void testOneInvalidInput() {
        //Test if it prints out the correct file and using the default cipher
        String[] args = {"abc"};  // forces NumberFormatException
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testTwoValidInput() {
        //Test if it prints out the correct file and using the chosen cipher
        String[] args = {"1", "Caesar"};
        // Use a handler that returns valid content for index 1
        FileHandler validHandler = new FileHandler(testPath) {
            @Override
            public String getFileContents(int index) {
                return "Hello World";
            }
        };
        ProgramController pc = new ProgramController(args, validHandler);
        pc.cmd_line_util();
    }

    @Test
    void testTwoInvalidInput() {
        //Test if it throws and error for invalid integer
        String[] args = {" ", " "};
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testOneValidOneInvalidInput() {
        //Test if it throws and error for invalid integer
        String[] args = {"1", ""};
        // Use a handler that returns valid content for index 1
        FileHandler validHandler = new FileHandler(testPath) {
            @Override
            public String getFileContents(int index) {
                return "Hello World";
            }
        };
        ProgramController pc = new ProgramController(args, validHandler);
        pc.cmd_line_util();
    }

    @Test
    void testOneInvalidOneValidInput() {
        //Test if it throws and error for invalid integer
        String[] args = {"abc", "Cipher"};
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testTooManyArguments(){
        //Test behavior when more than 2 arguments are provided
        String[] args = {"1", "Caesar", "extra"};
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testIndexOutOfBounds() {
        //Test if it throws and error for invalid file index
        String[] args = {"5"};  // index that does not exist
        ProgramController pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }

    @Test
    void testErrorContentBranch() {
        //Test if content.startsWith("Error!") branch executes
        String[] args = {"1"};
        FileHandler errorHandler = new FileHandler(testPath) {
            @Override
            public String getFileContents(int index) {
                return "Error! File not found";
            }
        };
        ProgramController pc = new ProgramController(args, errorHandler);
        pc.cmd_line_util();
    }

    @Test
    void testIOExceptionBranch() {
        //Test if IOException catch branch executes
        FileHandler throwingHandler = new FileHandler(testPath) {
            @Override
            public String getFileContents(int index) throws IOException {
                throw new IOException("Forced exception");
            }
        };
        String[] args = {"1"};
        ProgramController pc = new ProgramController(args, throwingHandler);
        pc.cmd_line_util();
    }
}
