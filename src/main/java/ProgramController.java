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
        int file_number;
        switch(arg_len){
            case(0):
                //Jenny this is your part
                //print file list

                break;
            case(1):
                //attempt conversion to int
                file_number = Integer.parseInt(args[0]);
                //Need to check to see if int is greater than number of files

                //Jenny this is your part
                //print file with default cipher

                break;
            case(2):
                //print file using the given cipher
                //attempt conversion to int
                file_number = Integer.parseInt(args[0]);
                //Need to check to see if int is greater than number of files

                // Jenny this is your part

                break;
            default:
                System.err.println("Error: too many arguments provided.");
                System.err.println("Please provide 0,1 or 2 arguments");
    }

    }
}
