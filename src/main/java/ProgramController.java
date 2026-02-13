import java.io.IOException;


public class ProgramController {
    private String[] args;
    private FileHandler fileHandler;

    /**
     * Commmand Line Utility
     */
    public ProgramController(String[] in_args, FileHandler fh) {
        this.args = in_args;
        this.fileHandler = fh;
    }

    public void cmd_line_util() {
        int arg_len = args.length;

        try {
            switch (arg_len) {
                case (0): //passing - returns list of available files
                    System.out.println("Fetching available files...");
                    String fileList = fileHandler.getFileContents(0);
                    System.out.println(fileList);
                    break;

                case (1):
                case (2):
                    // Convert first argument to integer index
                    int file_index = Integer.parseInt(args[0]);

                    // Request content from FileHandler
                    String content = fileHandler.getFileContents(file_index);

                    // Check if the returned string is an error message from FileHandler
                    if (content.startsWith("Error!")) {
                        System.out.println(content);
                    } else {
                        if (arg_len == 1) {
                            System.out.println("Displaying file " + file_index + " with default cipher:");
                            System.out.println(content);
                        } else {
                            // args[1] represents the cipher name/type
                            System.out.println("Displaying file " + file_index + " using cipher [" + args[1] + "]:");
                            System.out.println(content);
                        }
                    }
                    break;

                default:
                    System.out.println("Error: Too many arguments provided.");
                    System.out.println("Usage: java Main [file_index] [cipher_name]");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: The file selection must be a valid integer.");
        } catch (IOException e) {
            System.out.println("Error: A system error occurred while accessing the file folder.");
        }
    }
}
