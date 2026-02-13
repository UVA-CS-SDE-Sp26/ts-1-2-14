import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class FileHandlerTest {
    private FileHandler fileHandler;
    private File mockFolder;

    @BeforeEach
    void setUp() {
        // don't need to connect to the actual folder path, mockito handles this dependency
        fileHandler = new FileHandler("dummy/path/");
        mockFolder = Mockito.mock(File.class);

        fileHandler.setFolderPath("dummy/path/");
        try {
            var field = FileHandler.class.getDeclaredField("folder");
            field.setAccessible(true);
            field.set(fileHandler, mockFolder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        try (MockedStatic<FileHandler> fileHandlerMock = Mockito.mockStatic(FileHandler.class)) {
            Path expectedPath = Path.of("dummy/path/" + "b.txt");
            mockedFiles.when(() -> Files.exists(expectedPath)).thenReturn(true);
            mockedFiles.when(() -> Files.readString(expectedPath)).thenReturn("CONTENT OF B");

            String result = handler.getFileContents(2);
            assertEquals("CONTENT OF B", result);
        }
    }

    @Test
    void testGetFileContentsWhenIndexOutOfBounds() throws IOException {
        File file1 = mock(File.class);
        when(file1.isFile()).thenReturn(true);
        when(file1.getName()).thenReturn("a.txt");

        File[] mockFiles = {file1};
        when(mockFolder.listFiles()).thenReturn(mockFiles);

        try (MockedStatic<FileHandler> fileHandlerMock = Mockito.mockStatic(FileHandler.class)) {
            Path expectedPath = Path.of("dummy/path/" + "b.txt");
            mockedFiles.when(() -> Files.exists(expectedPath)).thenReturn(true);
            mockedFiles.when(() -> Files.readString(expectedPath)).thenReturn("CONTENT OF C");

            String result = handler.getFileContents(2);
            assertEquals("Error! Invalid request - File does not exist.", result);
        }
    }

}