import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CipherSimple {
    private static final String DEFAULT_KEY_PATH = "ciphers/key.txt";

    public String decipherUsingKey(String text, String keyPath) {
        String path = (keyPath == null || keyPath.isEmpty()) ? DEFAULT_KEY_PATH : keyPath;
        List<String> lines = loadKeyLines(path);


        if (lines == null || lines.size() < 2) {
            System.err.println("Error: Invalid or missing cipher key.");
            System.exit(1); // Program exits gracefully here
        }

        return decipher(text, lines.get(0), lines.get(1));
    }

    public String decipher(String text, String actualLine, String cipherLine) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            int index = cipherLine.indexOf(c);

            if (index != -1 && index < actualLine.length()) {
                result.append(actualLine.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public List<String> loadKeyLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasDuplicates(String s) {
        return s.chars().distinct().count() < s.length();
    }
}