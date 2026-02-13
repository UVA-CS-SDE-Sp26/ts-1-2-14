class ProgramControllerTest {

    @Test
    void testZeroInput() {
        String[] args= new String[0];
        ProgramController pc = new ProgramController(args);
        //test if it runs without crash
        pc.cmd_line_util();

    }
    @Test
    void testOneValidInput() {
        String[] args= new String[1];
        args[0]=("1");
        ProgramController pc = new ProgramController(args);
        //Test if it prints out the correct file and using the default cipher
        pc.cmd_line_util();

    }
    @Test
    void testOneInvalidInput() {
        String[] args = new String[1];
        args[0] = (" ");
        ProgramController pc = new ProgramController(args);
        //Test if it throws an error due to bad input
        assertThrows(NumberFormatException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                // This is the line that will now "explode"
                pc.cmd_line_util();
            }
        });
    }

    @Test
    void testTwoValidInput() {
        String[] args= new String[2];
        args[0]=("1");
        args[1]="cipher";
        ProgramController pc = new ProgramController(args);
        //Test if it prints out the correct file and using the chosen cipher
        pc.cmd_line_util();
    }
    @Test
    void testTwoInvalidInput() {
        String[] args= new String[2];
        args[0]=" ";
        args[1]=" ";
        ProgramController pc = new ProgramController(args);
        //Test if it throws and error for invalid integer
        assertThrows(NumberFormatException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                // This is the line that will now "explode"
                pc.cmd_line_util();
            }
        });
    }
    @Test
    void testOneValidOneInvalidInput() {
        String[] args= new String[2];
        args[0]=("1");
        args[1]=" ";
        ProgramController pc = new ProgramController(args);
        //Test if it throws and error for invalid integer

    }
    @Test
    void testOneInvalidOneValidInput() {
        String[] args= new String[2];
        args[0]=(" ");
        args[1]="Cipher";
        ProgramController pc = new ProgramController(args);
        //Test if it throws and error for invalid integer
        assertThrows(NumberFormatException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                // This is the line that will now "explode"
                pc.cmd_line_util();
            }
        });
    }
}

