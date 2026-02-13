public class ProgramController {
    String[] args;
    /**
     * Commmand Line Utility
     */
    public ProgramController(String[] in_args){
        args=in_args;
    }

    public void cmd_line_util(){
        int arg_len = args.length;
        switch(arg_len){
            case(0):
                //Jenny this is your part
                //print file list

                break;
            case(1):
                try {
                    //attempt conversion to int
                    int file_number = Integer.parseInt(args[1]);
                    //Need to check to see if int is greater than number of files

                    //Jenny this is your part
                    //print file with default cipher

                    break;
                } catch (NumberFormatException e) {
                    //handle catch
                    System.out.println("Error: Bad input detected input is either not an int or not a file option");
                }
                break;
            case(2):
                //print file using the given cipher
                try {
                    //attempt conversion to int
                    int file_number = Integer.parseInt(args[1]);
                    //Need to check to see if int is greater than number of files

                    //Jenny this is your part

                    break;
                } catch (NumberFormatException e) {
                    //handle catch
                    System.out.println("Error: Bad input detected input is either not an int or not a file option");
                    break;
                }
            default:
                System.out.println("Error: too many arguments provided.");
                System.out.println("Please provide 0,1 or 2 arguments");
    }

    }
}
