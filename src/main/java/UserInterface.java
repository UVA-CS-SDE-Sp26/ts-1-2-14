public class UserInterface {
    String[] args;
    /**
     * Commmand Line Utility
     */
    public UserInterface(String[] in_args){
        args=in_args;
    }

    public void cmd_line_util(){
        int arg_len = args.length;
        switch(arg_len){
            case(0):
                //print file list
                System.out.println("0 args");
                break;
            case(1):
                //print file with default cipher
                System.out.println("1 arg");
                break;
            case(2):
                //print file using the given cipher
                System.out.println("2 args");
                break;
            default:
                System.out.println("Error: too many parameter provided");
    }

    }
}
