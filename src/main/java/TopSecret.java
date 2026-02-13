
public class TopSecret {
    public static void main(String[] args){
        FileHandler fh = new FileHandler("data/");
        ProgramController  pc = new ProgramController(args, fh);
        pc.cmd_line_util();
    }
}
