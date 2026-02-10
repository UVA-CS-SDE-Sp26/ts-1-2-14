import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private String folderPath;

    public String getFolderPath() {
        return folderPath;
    }

    private Boolean isValidRequest(Path filePath) {
        return true;
    }

    public String getFileContents(String fileName) throws IOException {
        Path filePath = Paths.get(folderPath + fileName);
        if(isValidRequest(filePath)) {
            return Files.readString(filePath);
        }
        else {
            return "Error! Invalid request - File does not exist.";
        }
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public FileHandler(String folderPath) {
        this.folderPath = folderPath;
    }

    @Override
    public String toString() {
        return "FileHandler{" +
                "folderPath='" + folderPath + '\'' +
                '}';
    }
}
