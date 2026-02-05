/**
 * Commmand Line Utility
 */
public class TopSecret {
    public static void main(String[] args){
        int arg_len = args.length;
        switch(arg_len){
            case(0): System.out.println(0);
            break;
            case(1): System.out.println(1);
            break;
            case(2): System.out.println(2);
            break;
            default:
                System.out.println("Weird input");
        }
    }
}
