import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {
    private String folderPath;
    private File folder;

    private Boolean isValidRequest(Path filePath) {
        Path p = Paths.get(filePath.toString());
        return Files.exists(p);
    }

    // this is the main function (i.e. the only function that the program controller should be calling)
    // method parameters - int
    public String getFileContents(int fileIndex) throws IOException {
        File[] files = folder.listFiles();

        // no arguments passed - return a list of available files
        if (fileIndex == 0) {
            ArrayList<String> filenames = new ArrayList<>();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        filenames.add((i+1)+" "+files[i].getName()+"\n");
                    }
                }
            }
            return filenames.toString();
        }
        else {
            if (fileIndex < files.length) {
                Path filePath = Paths.get(folderPath + files[fileIndex-1].getName());
                if (isValidRequest(filePath)) {
                    return Files.readString(filePath);
                }
                else {
                    return "Error! Invalid request - File does not exist.";
                }
            } else {
                return "Error! Invalid request - File does not exist.";
            }
        }
    }

    public FileHandler(String folderPath) {
        this.folderPath = folderPath;
        this.folder = new File(folderPath);
    }
}
