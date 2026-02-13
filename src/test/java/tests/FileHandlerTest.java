import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class FileHandlerTest {
    private FileHandler fileHandler;
    private File mockFolder;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        // don't need to connect to the actual folder path, mockito handles this dependency
        fileHandler = new FileHandler("dummy/path/");
        mockFolder = mock(File.class);

        var field = FileHandler.class.getDeclaredField("folder");
        field.setAccessible(true);
        field.set(fileHandler, mockFolder);
    }

    @Test
    void testGetFileContentsWhenIndexIsZero() throws IOException {
        // Mock files inside the folder
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        File file3 = mock(File.class);

        when(file1.isFile()).thenReturn(true);
        when(file2.isFile()).thenReturn(true);
        when(file3.isFile()).thenReturn(true);

        when(file1.getName()).thenReturn("a.txt");
        when(file2.getName()).thenReturn("b.txt");
        when(file3.getName()).thenReturn("c.txt");

        File[] mockFiles = {file1, file2, file3};
        when(mockFolder.listFiles()).thenReturn(mockFiles);

        String result = fileHandler.getFileContents(0);

        assertTrue(result.contains("1 a.txt"));
        assertTrue(result.contains("2 b.txt"));
        assertTrue(result.contains("3 c.txt"));
    }

    @Test
    void testGetFileContentsWhenIndexIsNotZero() throws IOException {
        // Mock files inside the folder
        File file1 = mock(File.class);
        File file2 = mock(File.class);
        File file3 = mock(File.class);

        when(file1.isFile()).thenReturn(true);
        when(file2.isFile()).thenReturn(true);
        when(file3.isFile()).thenReturn(true);

        when(file1.getName()).thenReturn("a.txt");
        when(file2.getName()).thenReturn("b.txt");
        when(file3.getName()).thenReturn("c.txt");

        File[] mockFiles = {file1, file2, file3};
        when(mockFolder.listFiles()).thenReturn(mockFiles);
        String result = fileHandler.getFileContents(2);
        assertEquals("CONTENT OF B", result);
    }

    @Test
    void testGetFileContentsWhenIndexOutOfBounds() throws IOException {
        File file1 = mock(File.class);
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("a.txt");

        File[] mockFiles = {file1};
        when(mockFolder.listFiles()).thenReturn(mockFiles);
        String result = fileHandler.getFileContents(2);
        assertEquals("Error! Invalid request - File does not exist.", result);
    }
}