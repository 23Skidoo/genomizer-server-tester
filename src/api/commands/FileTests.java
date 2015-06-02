package api.commands;

import api.commands.File.*;
import model.Debug;

/**
 * Created by c10mjn on 2015-05-26.
 */
public class FileTests extends TestCollection {
    public FileTests() {
        super();
        CommandTester.fileID = new StringContainer("");

        FileTuple ft1 = new FileTuple();
        ft1.setId(CommandTester.EXP_NAME);
        ft1.setName("test.fastq");
        ft1.setAuthor("testuser");
        ft1.setMetaData("f1");
        ft1.setType("raw");
        ft1.setGrVersion("hg38");

        FileTuple ft2 = new FileTuple();
        ft2.setId(CommandTester.EXP_NAME);
        ft2.setName("test.fastq");
        ft2.setAuthor("testuser");
        ft2.setMetaData("f2");
        ft2.setType("profile");
        ft2.setGrVersion("hg38");

        FileTuple ft3 = new FileTuple();
        ft3.setId(CommandTester.EXP_NAME);
        ft3.setName("test.fastq");
        ft3.setAuthor("testuser");
        ft3.setMetaData("f3");
        ft3.setType("profilist");
        ft3.setGrVersion("hg38");

        FileTuple ft4 = new FileTuple();
        ft4.setId(CommandTester.EXP_NAME);
        ft4.setName("test.fastq");
        ft4.setAuthor("testuser");
        ft4.setMetaData("f4");
        ft4.setType("profile");
        ft4.setGrVersion("hg1337");

        super.commandList.add(new PostFileTest("POST FILE1", ft1, true));
        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,1, true));
        super.commandList.add(new GetFileTest("GET FILE META F1", "f1", true));

        //Put file doesn't seem to be working at the moment.
//        super.commandList.add(new PutFileTest("PUT FILE F2", ft2, "", true));
//        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,1, true));
//        super.commandList.add(new GetFileTest("GET FILE META F2", "f2", true));
//        super.commandList.add(new PutFileTest("PUT FILE F3", ft3, "", false));

        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,1, true));
        super.commandList.add(new GetFileTest("GET NOT FILE META F3", "f3", false));

        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,-1, true));
        super.commandList.add(new DeleteFileTest("DELETE FILE1", null, true));

        super.commandList.add(new PostFileTest("POST FILE WRONG TYPE", ft3, true));
        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,-1, true));
        super.commandList.add(new GetFileTest("GET FILE TYPE OTHER", "Other", true));
        super.commandList.add(new DeleteFileTest("CLEANUP", null, true));

        super.commandList.add(new PostFileTest("POST FILE WRONG GR", ft4, false));
        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,-1, true));
        super.commandList.add(new GetFileTest("GET NONEXISTING EXPERIMENT FILE", "testuser", false));
        super.commandList.add(new DeleteFileTest("CLEANUP", null, false));

        super.commandList.add(new PostFileTest("POST FIRST FILE", ft1, true));
        super.commandList.add(new PostFileTest("POST DUPLICATE FILE", ft1, false));
        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,-1, true));
        super.commandList.add(new DeleteFileTest("CLEANUP", null, true));
        super.commandList.add(new ChangeIndex("CHANGE INDEX", CommandTester.EXP_NAME,0,-1, true));
        super.commandList.add(new DeleteFileTest("DELETE NONEXISTING FILE", null, false));
    }

    @Override
    public boolean execute() {
        System.out.println("\n-----------------------FILE----------------------");
        boolean bigResult = true;
        for (SuperTestCommand stc: super.commandList) {
            stc.execute();

            if ( stc instanceof ChangeIndex) {
                if (!stc.finalResult) {
                    nameOfFailedTests.add(stc.ident);
                    bigResult = false;
                }
                continue;
            }

            runTests++;

            boolean succeeded = stc.finalResult == stc.expectedResult;

            if (succeeded){
                succeededTests++;
            } else {
                failedTests++;
                nameOfFailedTests.add(stc.ident);
                bigResult = false;
            }

            Debug.log(stc.toString(), succeeded);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return bigResult;
    }
}
